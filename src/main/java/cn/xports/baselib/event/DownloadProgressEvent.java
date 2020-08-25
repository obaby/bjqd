package cn.xports.baselib.event;

public class DownloadProgressEvent {
    private long readBytes;
    private long total;

    public DownloadProgressEvent(long j, long j2) {
        this.total = j;
        this.readBytes = j2;
    }

    public long getReadBytes() {
        return this.readBytes;
    }

    public void setReadBytes(long j) {
        this.readBytes = j;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long j) {
        this.total = j;
    }
}
