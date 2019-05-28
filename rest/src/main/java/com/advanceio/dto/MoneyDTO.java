package com.advanceio.dto;

public class MoneyDTO {
    public enum Type {
        COIN
    };

    private Type type;
    private double value;
    private int amount;

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
