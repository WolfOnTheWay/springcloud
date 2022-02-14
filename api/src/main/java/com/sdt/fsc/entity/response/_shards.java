package com.sdt.fsc.entity.response;

import java.io.Serializable;

public class _shards implements Serializable {
    private static final long serialVersionUID = -6449602412836442528L;
    private int total;
    private int successful;
    private int failed;
    public void setTotal(int total) {
        this.total = total;
    }
    public int getTotal() {
        return total;
    }

    public void setSuccessful(int successful) {
        this.successful = successful;
    }
    public int getSuccessful() {
        return successful;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }
    public int getFailed() {
        return failed;
    }
}
