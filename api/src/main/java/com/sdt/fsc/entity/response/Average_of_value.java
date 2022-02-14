package com.sdt.fsc.entity.response;

import java.io.Serializable;

public class Average_of_value implements Serializable {
    private static final long serialVersionUID = 6062121660944305552L;
    private double value;
    private String sum;
    private String count;

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getSum() {
        return sum;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCount() {
        return count;
    }
}
