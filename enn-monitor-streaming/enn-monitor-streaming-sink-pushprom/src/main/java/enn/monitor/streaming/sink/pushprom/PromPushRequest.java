package enn.monitor.streaming.sink.pushprom;

import java.util.Map;

/**
 * Created by weize on 17-5-11.
 *
 *  {
 *      "type": "gauge",
 *      "name": "trees",
 *      "help": "the amount of trees in the forest.",
 *      "method": "add",
 *      "value": 3002,
 *      "labels": {
 *          "species": "araucaria angustifolia",
 *          "job": "tree-counter-bot"
 *      }
 *  }
 */
public class PromPushRequest {
    private String type;
    private String name;
    private String help;
    private String method;
    private double value;
    private Map<String, String> labels;

    public PromPushRequest(String type, String name, String help, String method, double value, Map<String, String> labels) {
        this.type = type;
        this.name = name;
        this.help = help;
        this.method = method;
        this.value = value;
        this.labels = labels;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getHelp() {
        return help;
    }

    public String getMethod() {
        return method;
    }

    public double getValue() {
        return value;
    }

    public Map<String, String> getLabels() {
        return labels;
    }
}


