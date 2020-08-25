package cn.bingoogolapple.bgabanner;

public class BGALocalImageSize {
    private int maxHeight;
    private int maxWidth;
    private float minHeight;
    private float minWidth;

    public BGALocalImageSize(int i, int i2, float f, float f2) {
        this.maxWidth = i;
        this.maxHeight = i2;
        this.minWidth = f;
        this.minHeight = f2;
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    public void setMaxWidth(int i) {
        this.maxWidth = i;
    }

    public int getMaxHeight() {
        return this.maxHeight;
    }

    public void setMaxHeight(int i) {
        this.maxHeight = i;
    }

    public float getMinWidth() {
        return this.minWidth;
    }

    public void setMinWidth(float f) {
        this.minWidth = f;
    }

    public float getMinHeight() {
        return this.minHeight;
    }

    public void setMinHeight(float f) {
        this.minHeight = f;
    }
}
