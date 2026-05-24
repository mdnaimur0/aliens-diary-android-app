package com.naimur.aliensdiary.core.listener;

public interface RequestListener<T> {

    void onSuccess(T result);

    void onFailure(Exception e);
}
