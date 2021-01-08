package com.example.testproiect.coduri.promotionale;

import android.content.Context;


import com.example.testproiect.asyncTask.AsyncTaskRunner;
import com.example.testproiect.asyncTask.Callback;
import com.example.testproiect.wishlist.DatabaseManager;

import java.util.List;
import java.util.concurrent.Callable;

public class CodPromotionalOperations {
    private final CodPromotionalDAO codPromotionalDAO;
    private final AsyncTaskRunner taskRunner;
    public CodPromotionalOperations(Context context) {
        codPromotionalDAO = DatabaseManager.getInstance(context).getCodPromotionalDao();
        taskRunner = new AsyncTaskRunner();
    }
    public List<CodPromotional> getAllV2() {
        return codPromotionalDAO.getAll();
    }

    public void getAll(Callback<List<CodPromotional>> callback) {
        Callable<List<CodPromotional>> callable = new Callable<List<CodPromotional>>() {
            @Override
            public List<CodPromotional> call() {
                return codPromotionalDAO.getAll();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
    public void insert(Callback<CodPromotional> callback, final CodPromotional cp) {
        Callable<CodPromotional> callable = new Callable<CodPromotional>() {
            @Override
            public CodPromotional call() {
                if (cp == null) {
                    return null;
                }
                long id = codPromotionalDAO.insert(cp);
                if (id == -1) {
                    return null;
                }
                cp.setId(id);
                return cp;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void update(Callback<CodPromotional> callback, final CodPromotional cp) {
        Callable<CodPromotional> callable = new Callable<CodPromotional>() {
            @Override
            public CodPromotional call() {
                if (cp == null) {
                    return null;
                }
                int count = codPromotionalDAO.update(cp);
                if (count < 1) {
                    return null;
                }
                return cp;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void delete(Callback<Integer> callback, final CodPromotional codPromo) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                if (codPromo == null) {
                    return -1;
                }
                return codPromotionalDAO.delete(codPromo);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
}
