package enn.monitor.framework.tracing.bytebuddy;

/**
 * Created by weize on 17-11-17.
 */
public class ExampleTypeDefaultConstructor {
    private String name;
    public String doSomething() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
