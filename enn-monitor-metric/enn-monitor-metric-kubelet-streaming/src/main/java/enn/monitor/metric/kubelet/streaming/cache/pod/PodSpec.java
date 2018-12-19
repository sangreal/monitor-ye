package enn.monitor.metric.kubelet.streaming.cache.pod;

import java.io.Serializable;
import java.util.List;

/**
 * Created by weize on 17-10-23.
 */
public class PodSpec implements Serializable {
    private List<Container> containers;

    public List<Container> getContainers() {
        return containers;
    }

    public class Container implements Serializable {
        private String name;
        private Quotas resources;

        public String getName() {
            return name;
        }

        public Quotas getResources() {
            return resources;
        }
    }

    public class Quota implements Serializable {
        private String cpu;
        private String memory;

        /**
         * 两种写法："300m"，表示0.3
         * 或者直接写数字
         */
        public float getCpu() {
            try {
                cpu = cpu.toLowerCase();
                if (cpu.contains("m")) {
                    return Float.parseFloat(cpu.replaceAll("m", "")) / 1000;
                } else {
                    return Float.parseFloat(cpu);
                }
            } catch (NumberFormatException e) {
                System.out.println("error parsing cpu quota : " + cpu);
                return -1;
            }
        }

        public long getMemory() {
            try {
                memory = memory.toLowerCase();
                if (memory.contains("ti")) {
                    return (long) (Float.parseFloat(memory.replaceAll("ti", ""))) * 1024 * 1024 * 1024 * 1024;
                } else if (memory.contains("gi")) {
                    return (long) (Float.parseFloat(memory.replaceAll("gi", ""))) * 1024 * 1024 * 1024;
                } else if (memory.contains("mi")) {
                    return (long) (Float.parseFloat(memory.replaceAll("mi", ""))) * 1024 * 1024;
                } else if (memory.contains("ki")) {
                    return (long) (Float.parseFloat(memory.replaceAll("ki", ""))) * 1024;
                } else if (memory.contains("t")) {
                    return (long) (Float.parseFloat(memory.replaceAll("t", ""))) * 1000 * 1000 * 1000 * 1000;
                }else if (memory.contains("g")) {
                    return (long) (Float.parseFloat(memory.replaceAll("g", ""))) * 1000 * 1000 * 1000;
                } else if (memory.contains("m")) {
                    return (long) (Float.parseFloat(memory.replaceAll("m", ""))) * 1000 * 1000;
                } else if (memory.contains("k")) {
                    return (long) (Float.parseFloat(memory.replaceAll("k", ""))) * 1000;
                }  else {
                    return Long.parseLong(memory);
                }
            } catch (NumberFormatException e) {
                System.out.println("error parsing memory quota : " + memory);
                return -1;
            }
        }

    }

    public class Quotas implements Serializable {
        private Quota limits;
        private Quota requests;

        public Quota getLimits() {
            return limits;
        }

        public Quota getRequests() {
            return requests;
        }
    }
}
