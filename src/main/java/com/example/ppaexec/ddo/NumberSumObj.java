package com.example.ppaexec.ddo;

import lombok.Getter;

import java.util.Objects;

@Getter
public class NumberSumObj {

    private Integer a;
    private Integer b;
    private Integer sum;

    public void setA(Integer a) {
        this.a = a;
        this.sum = this.a + this.b;
    }

    public void setB(Integer b) {
        this.b = b;
        this.sum = this.a + this.b;
    }

    public NumberSumObj(Integer a, Integer b) {
        this.a = a;
        this.b = b;
        this.sum = a + b;
    }

    public Boolean searchNumber(Integer num)  {
        return Objects.equals(this.a, num) || Objects.equals(this.b, num) || Objects.equals(this.sum, num);
    }
}
