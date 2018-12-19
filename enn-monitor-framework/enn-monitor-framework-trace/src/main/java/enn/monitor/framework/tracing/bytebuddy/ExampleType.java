package enn.monitor.framework.tracing.bytebuddy;

/**
 * Created by weize on 17-11-14.
 */
public class ExampleType {
    private String name;
    private int index;
    private float[] metrics;

    public ExampleType(String name, int index, float[] metrics) {
        this.name = name;
        this.index = index;
        this.metrics = metrics;
    }

    public String doSomething() {
        return name + "-" + index;
    }

    public float[] getMetrics() {
        return metrics;
    }
}
