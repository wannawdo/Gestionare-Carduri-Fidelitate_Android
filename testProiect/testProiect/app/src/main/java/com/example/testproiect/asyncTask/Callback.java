package com.example.testproiect.asyncTask;

//punem <R> ca sa marcam clasa ca fiind generica
public interface Callback<R> {
    void runResultOnUiThread(R result);
}
