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

    protected abstract long store(T bean);

    protected abstract int updateById(T bean);

    protected abstract int deleteByIds(List<Long> ids);

    protected abstract int deleteAll();

    protected abstract T queryById(long id);

    protected abstract List<T> queryByIdOffset(Long offset, int size);

    public void close() {
        dbHelper.close();
        db.close();
    }
}
