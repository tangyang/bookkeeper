package com.weibo.bookkeeper.store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HuangXiao on 14-8-10.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int CURRENT_VERSION = 1;

    private static final String DATABASE_NAME = "BookKeeper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AccountStore.CREATE_TABLE_SQL);
        db.execSQL(TransactionStore.CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //for update db structure
    }
}
