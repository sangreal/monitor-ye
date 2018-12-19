package enn.monitor.trace.proto.model.common;

import java.util.List;

/**
 * Created by weize on 18-1-7.
 */
public class ListData<T> {
    private List<T> list;
    private int totalCount;
    private int pageSize;
    private int pageIndex;
    public static final long DEFAULT_INTERVAL = 60 * 1000 * 1000; // 1 minute
    private long interval = DEFAULT_INTERVAL; // in us

    public ListData(List<T> list, int totalCount, int pageSize, int pageIndex) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
    }

    public List<T> getList() {
        return list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public long getInterval() {
        return interval;
    }
}
