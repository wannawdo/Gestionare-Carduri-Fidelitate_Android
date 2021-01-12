package com.example.testproiect.asyncTask;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.testproiect.R;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsyncTaskRunner {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Executor executor = Executors.newCachedThreadPool();

    public <R> void executeAsync(Callable<R> asyncOperation, Callback<R> mainThreadOperation) {
        try {
            //ii specificam Executor-ului ca dorim sa procesam un Thread de tipul clasei RunnableTask
            executor.execute(new RunnableTask<>(handler, asyncOperation, mainThreadOperation));
        } catch (Exception ex) {
            Log.i("AsyncTaskRunner", "eroare runnable" + ex.getMessage());
        }
    }
}
