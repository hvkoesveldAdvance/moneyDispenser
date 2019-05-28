package com.advanceio.entity;

public abstract class Entity<T> {

    public abstract T getId();

    public abstract void setId(T id);

}