package anetwork.channel;

import anetwork.channel.statist.StatisticData;

/* compiled from: Taobao */
public class NetworkEvent {

    /* compiled from: Taobao */
    public interface FinishEvent {
        String getDesc();

        int getHttpCode();

        StatisticData getStatisticData();
    }

    /* compiled from: Taobao */
    public interface ProgressEvent {
        byte[] getBytedata();

        String getDesc();

        int getIndex();

        int getSize();

        int getTotal();
    }
}
