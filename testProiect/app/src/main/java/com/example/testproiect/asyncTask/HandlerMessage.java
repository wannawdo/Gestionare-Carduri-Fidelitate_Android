package com.example.testproiect.asyncTask;

public class HandlerMessage <R> implements Runnable {
    //final ca sa nu mai modific dupa
    //este trimis RunnableTask dupa ce a fost primi din firul principal (activitate/fragment)
    private final Callback<R> mainThreadOperation;
    //este trimis din RunnableTask
    private final R result;

    public HandlerMessage(Callback<R> mainThreadOperation, R result) {
        this.mainThreadOperation = mainThreadOperation;
        this.result = result;
    }
    @Override
    public void run() {
//se trimite rezultatul in activitate/fragment
        mainThreadOperation.runResultOnUiThread(result);
    }
}
