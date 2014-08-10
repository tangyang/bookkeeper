package com.weibo.bookkeeper.store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by HuangXiao on 14-8-10.
 */
public abstract class AbstractStore<T> {

    protected DatabaseHelper dbHelper;

    protected SQLiteDatabase db;

    public AbstractStore(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    protected abstract boolean store(T bean);

    protected abstract boolean deleteByIds(List<Long> ids);

    protected abstract boolean deleteAll();

    protected abstract T queryById(long id);

    protected abstract List<T> queryByPage(int page, int size);

    public void close() {
        dbHelper.close();
        db.close();
    }
}
