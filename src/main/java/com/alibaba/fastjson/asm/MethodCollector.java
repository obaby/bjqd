package com.alibaba.fastjson.asm;

public class MethodCollector {
    private int currentParameter;
    protected boolean debugInfoPresent;
    private final int ignoreCount;
    private final int paramCount;
    private final StringBuffer result = new StringBuffer();

    protected MethodCollector(int i, int i2) {
        this.ignoreCount = i;
        this.paramCount = i2;
        boolean z = false;
        this.currentParameter = 0;
        this.debugInfoPresent = i2 == 0 ? true : z;
    }

    /* access modifiers changed from: protected */
    public void visitLocalVariable(String str, int i) {
        if (i >= this.ignoreCount && i < this.ignoreCount + this.paramCount) {
            if (!str.equals("arg" + this.currentParameter)) {
                this.debugInfoPresent = true;
            }
            this.result.append(',');
            this.result.append(str);
            this.currentParameter++;
        }
    }

    /* access modifiers changed from: protected */
    public String getResult() {
        return this.result.length() != 0 ? this.result.substring(1) : "";
    }
}
