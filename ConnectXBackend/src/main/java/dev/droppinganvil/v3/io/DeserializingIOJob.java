/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.io;

import dev.droppinganvil.v3.annotations.Accessible;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Object for easily deserializing objects.
 * All calls are made on IO threads, please keep this in mind
 * Field and/or Method MUST have Accessible annotation
 */
public class DeserializingIOJob extends IOJob {
    public Method fireAfter;
    public Object o;
    public Field var;
    public String varName;
    public DeserializingIOJob(InputStream is, OutputStream os, Boolean closeAfter) {
        super(is, os, closeAfter);
    }
    //TODO look into using managed persistence
    @Override
    public void doAfter(boolean success) {
        try {
            if (var != null && var.isAnnotationPresent(Accessible.class)) {
                var.set(o, );
            }
            if (fireAfter != null && fireAfter.isAnnotationPresent(Accessible.class)) {
                fireAfter.invoke(o, this);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
