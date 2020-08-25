package anet.channel.statist;

/* compiled from: Taobao */
public class CountObject {
    public String arg;
    public String module;
    public String modulePoint;
    public double value;

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("[module:");
        sb.append(this.module);
        sb.append(" modulePoint:");
        sb.append(this.modulePoint);
        sb.append(" arg:");
        sb.append(this.arg);
        sb.append(" value:");
        sb.append(this.value);
        sb.append("]");
        return sb.toString();
    }
}
