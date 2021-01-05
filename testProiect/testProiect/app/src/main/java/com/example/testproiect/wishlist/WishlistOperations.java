package com.example.testproiect.wishlist;

import android.content.Context;

import com.example.testproiect.asyncTask.AsyncTaskRunner;
import com.example.testproiect.asyncTask.Callback;

import java.util.List;
import java.util.concurrent.Callable;

public class WishlistOperations {
    private final WishlistDAO wishlistDAO;
    private final AsyncTaskRunner taskRunner;
    public WishlistOperations(Context context) {
        wishlistDAO = DatabaseManager.getInstance(context).getWishlistDao();
        taskRunner = new AsyncTaskRunner();
    }
    public List<Wishlist> getAllV2() {
        return wishlistDAO.getAll();
    }

    public void getAll(Callback<List<Wishlist>> callback) {
        Callable<List<Wishlist>> callable = new Callable<List<Wishlist>>() {
            @Override
            public List<Wishlist> call() {
                return wishlistDAO.getAll();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
    public void insert(Callback<Wishlist> callback, final Wishlist wishlist) {
        Callable<Wishlist> callable = new Callable<Wishlist>() {
            @Override
            public Wishlist call() {
                if (wishlist == null) {
                    return null;
                }
                long id = wishlistDAO.insert(wishlist);
                if (id == -1) {
                    return null;
                }
                wishlist.setId(id);
                return wishlist;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void update(Callback<Wishlist> callback, final Wishlist expense) {
        Callable<Wishlist> callable = new Callable<Wishlist>() {
            @Override
            public Wishlist call() {
                if (expense == null) {
                    return null;
                }
                int count = wishlistDAO.update(expense);
                if (count < 1) {
                    return null;
                }
                return expense;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void delete(Callback<Integer> callback, final Wishlist expense) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                if (expense == null) {
                    return -1;
                }
                return wishlistDAO.delete(expense);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

}
