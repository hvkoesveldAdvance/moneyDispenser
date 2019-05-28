package com.advanceio.entity;

public class Money {
    public enum Type {
        COIN
    };

    private Type type;
    private double value;
    private int amount;

    public Money(Type type, double value, int amount) {
        this.type = type;
        this.value = value;
        this.amount = amount;
    }

    public void withdraw(int numberForCurrentItem) {
        this.amount -= numberForCurrentItem;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
