package enn.monitor.framework.tracing;

import enn.monitor.framework.tracing.bytebuddy.*;
import enn.monitor.framework.tracing.model.TraceAccessor;
import enn.monitor.framework.tracing.model.TraceInfo;
import enn.monitor.framework.tracing.model.ZipkinTraceInfo;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by weize on 17-11-14.
 */
public class TracingWrapper<T> {
    private static final Map<Class<?>, Class<?>> OUTBOX_MAP = new HashMap() {{
        put(Integer.class, int.class);
        put(Long.class, long.class);
        put(Float.class, float.class);
        put(Double.class, double.class);
    }};

    public static Map<Integer, Class> classCache = new ConcurrentHashMap<>();
    private static final ByteBuddy byteBuddy = new ByteBuddy();

    public T wrapObject(Class c) throws Exception {
        return wrapObject(c, new ArrayList<>());
    }

    /**
     *
     * @param c: The class to wrap
     * @param params: Constructor params
     * @return instance of new wrapped class(which is subclass of c), transparent to user of c;
     * @throws Exception
     */
    public T wrapObject(Class c, List<Object> params) throws Exception {
        Class<? extends T> wrappedClass = classCache.get(c.hashCode());
        if (wrappedClass == null) {
            wrappedClass = byteBuddy
                    .subclass(c)
                    .defineField("ennMonitorTraceInfo", TraceInfo.class, Visibility.PRIVATE)
                    .implement(TraceAccessor.class).intercept(FieldAccessor.ofBeanProperty())
                    .make()
                    .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                    .getLoaded();
            classCache.put(c.hashCode(), wrappedClass);
        }

        Class[] constructorParamClasses = new Class[params.size()];
        Object[] realParams = new Object[params.size()];
        for (int i = 0; i < params.size(); i ++) {
            Class clazz = params.get(i).getClass();
            if (OUTBOX_MAP.containsKey(clazz)) clazz = OUTBOX_MAP.get(clazz);
            constructorParamClasses[i] = clazz;
            realParams[i] = params.get(i);
        }
        T wrappedInstance = wrappedClass.getDeclaredConstructor(constructorParamClasses)
                .newInstance(realParams);

        ((TraceAccessor) wrappedInstance).setEnnMonitorTraceInfo(new ZipkinTraceInfo());
        ((TraceAccessor) wrappedInstance).getEnnMonitorTraceInfo().getInfo();
        return wrappedInstance;
    }

    public static void main(String[] args) throws Exception {
        TracingWrapper<ExampleType> wrapper = new TracingWrapper<>();

        List<Object> constructParams = new ArrayList();
        constructParams.add("test-name");
        constructParams.add(1);
        constructParams.add(new float[]{1.0f, 2.0f});
        ExampleType wrappedObject = wrapper.wrapObject(ExampleType.class, constructParams);

        TraceInfo traceInfo = ((TraceAccessor) wrappedObject).getEnnMonitorTraceInfo();
        System.out.println(wrappedObject.doSomething());

        TracingWrapper<ExampleTypeDefaultConstructor> wrapper2 = new TracingWrapper<>();
        ExampleTypeDefaultConstructor wrappedObject2 = wrapper2.wrapObject(ExampleTypeDefaultConstructor.class);
        wrappedObject2.setName("test-name2");

        traceInfo = ((TraceAccessor) wrappedObject2).getEnnMonitorTraceInfo();
        traceInfo.getInfo().put("test-key", "test-value");
        System.out.println(wrappedObject2.doSomething());
        System.out.println(((TraceAccessor) wrappedObject2).getEnnMonitorTraceInfo().getInfo().get("test-key"));
    }
}
