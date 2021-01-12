package com.example.testproiect.asyncTask;

// <R> - clasa generica
public interface Callback<R> {
    void runResultOnUiThread(R result);
}
