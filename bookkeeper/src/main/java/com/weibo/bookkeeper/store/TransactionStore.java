package com.weibo.bookkeeper.store;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.weibo.bookkeeper.model.Transaction;
import com.weibo.bookkeeper.util.MixAll;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangXiao on 14-8-10.
 */
public class TransactionStore extends AbstractStore<Transaction> {

    private static final String TABLE_NAME = "Transaction";

    private static final String ID = "_id";
    private static final int ID_INDEX = 0;

    private static final String NAME = "name";
    private static final int NAME_INDEX = 1;

    private static final String TYPE = "type";
    private static final int TYPE_INDEX = 2;

    private static final String AMOUNT = "amount";
    private static final int AMOUNT_INDEX = 3;

    private static final String CURRENCY_CODE = "currency_code";
    private static final int CURRENCY_CODE_INDEX = 4;

    private static final String DESCRIPTION = "description";
    private static final int DESCRIPTION_INDEX = 5;

    private static final String CREATE_TIME = "create_time";
    private static final int CREATE_TIME_INDEX = 6;

    private static final String ACCOUNT_ID = "account_id";
    private static final int ACCOUNT_ID_INDEX = 7;

    static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME  + " ("
            + ID            + " integer primary key autoincrement, "
            + NAME                      + " varchar(255), "
            + TYPE                      + " varchar(255) not null, "
            + AMOUNT            + " varchar(255) not null, "
            + CURRENCY_CODE            + " varchar(255) not null, "
            + DESCRIPTION       + " text, "
            + CREATE_TIME         + " integer not null, "
            + ACCOUNT_ID       + " integer not null);";

    public TransactionStore(Context context) {
        super(context);
    }

    @Override
    protected long store(Transaction bean) {
        ContentValues values = new ContentValues();
        values.put(NAME, bean.getName());
        values.put(TYPE, bean.getType());
        values.put(AMOUNT, String.valueOf(bean.getAmount()));
        values.put(CURRENCY_CODE, bean.getCurrencyCode());
        values.put(DESCRIPTION, bean.getDescription());
        values.put(CREATE_TIME, bean.getCreateTime());
        values.put(ACCOUNT_ID, bean.getAccountId());
        return db.insert(TABLE_NAME, null, values);
    }

    @Override
    protected int updateById(Transaction bean) {
        ContentValues values = new ContentValues();
        if(bean.getName() != null) {
            values.put(NAME, bean.getName());
        }
        if(bean.getType() != null) {
            values.put(TYPE, bean.getType());
        }
        if(bean.getAmount() != null) {
            values.put(AMOUNT, bean.getAmount());
        }
        if(bean.getCurrencyCode() != null) {
            values.put(CURRENCY_CODE, bean.getCurrencyCode());
        }
        if(bean.getDescription() != null) {
            values.put(DESCRIPTION, bean.getDescription());
        }
        if(bean.getCreateTime() != null) {
            values.put(CREATE_TIME, bean.getCreateTime());
        }
        if(bean.getAccountId() != null) {
            values.put(ACCOUNT_ID, bean.getAccountId());
        }
        return db.update(TABLE_NAME, values, ID + " = " + bean.getId(), null);
    }

    @Override
    protected int deleteByIds(List<Long> ids) {
        StringBuilder sb = new StringBuilder(ID).append(" in (");
        int length = ids.size();
        for(int i = 0; i < length; i++) {
            if(ids.get(i) != null) {
                if(i == length - 1) {
                    sb.append(ids.get(i)).append(")");
                } else {
                    sb.append(ids.get(i)).append(", ");
                }
            }
        }
        return db.delete(TABLE_NAME, sb.toString(), null);
    }

    @Override
    protected int deleteAll() {
        return db.delete(TABLE_NAME, null, null);
    }

    public int deleteByAccountId(List<Long> accountIds) {
        StringBuilder sb = new StringBuilder(ACCOUNT_ID).append(" in (");
        int length = accountIds.size();
        for(int i = 0; i < length; i++) {
            if(accountIds.get(i) != null) {
                if(i == length - 1) {
                    sb.append(accountIds.get(i)).append(")");
                } else {
                    sb.append(accountIds.get(i)).append(", ");
                }
            }
        }
        return db.delete(TABLE_NAME, sb.toString(), null);
    }

    @Override
    protected Transaction queryById(long id) {
        android.database.Cursor cursor = db.query(TABLE_NAME, null, ID + " = " + id, null, null, null, null, null);
        if(cursor == null) {
            return null;
        } else if(cursor.getCount() <= 0) {
            cursor.close();
            return null;
        } else {
            cursor.moveToFirst();
            Transaction transaction = buildTransaction(cursor);
            cursor.close();
            return transaction;
        }
    }

    private Transaction buildTransaction(Cursor cursor) {
        long id = cursor.getLong(ID_INDEX);
        String name = cursor.getString(NAME_INDEX);
        String type = cursor.getString(TYPE_INDEX);
        Double account = MixAll.toDoubleOrNull(cursor.getString(ACCOUNT_ID_INDEX));
        String currencyCode = cursor.getString(CURRENCY_CODE_INDEX);
        String description = cursor.getString(DESCRIPTION_INDEX);
        long createTime = cursor.getLong(CREATE_TIME_INDEX);
        long accountId = cursor.getLong(ACCOUNT_ID_INDEX);
        return new Transaction(id, name, type, account, currencyCode, description, createTime, accountId);
    }

    @Override
    protected List<Transaction> queryByIdOffset(Long offset, int size) {
        Cursor cursor = db.query(TABLE_NAME, null, CREATE_TIME + " < " + offset, null, null, null, CREATE_TIME + " DESC", String.valueOf(size));
        if(cursor == null) {
            return null;
        } else if(cursor.getCount() <= 0) {
            cursor.close();
            return null;
        } else {
            List<Transaction> list = new ArrayList<Transaction>(cursor.getCount());
            while(cursor.moveToNext()) {
                list.add(buildTransaction(cursor));
            }
            cursor.close();
            return list;
        }
    }
}
