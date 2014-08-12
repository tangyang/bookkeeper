package com.weibo.bookkeeper.store;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.weibo.bookkeeper.model.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangXiao on 14-8-10.
 */
public class AccountStore extends AbstractStore<Account> {

    private static final String TABLE_NAME = "account";

    private static final String ID = "_id";
    private static final int ID_INDEX = 0;

    private static final String ACCOUNT_NAME = "name";
    private static final int ACCOUNT_NAME_INDEX = 1;

    //本位币
    private static final String BASE_CURRENCY = "base_currency";
    private static final int BASE_CURRENCY_INDEX = 2;

    private static final String TYPE = "type";
    private static final int TYPE_INDEX = 3;

    private static final String PARENT_ACCOUNT_ID = "parent";
    private static final int PARENT_ACCOUNT_ID_INDEX = 4;

    private static final String FULL_NAME = "full_name";
    private static final int FULL_NAME_INDEX = 5;

    private static final String DESCRIPTION = "description";
    private static final int DESCRIPTION_INDEX = 6;

    static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
            + ID + " integer primary key autoincrement, "
            + ACCOUNT_NAME      + " varchar(255) not null, "
            + TYPE      + " varchar(255) not null, "
            + BASE_CURRENCY + " varchar(255) not null, "
            + PARENT_ACCOUNT_ID + " integer, "
            + FULL_NAME + " varchar(255), "
            + DESCRIPTION + " varchar(2000));";

    private TransactionStore transactionStore;

    public AccountStore(Context context) {
        super(context);
        transactionStore = new TransactionStore(context);
    }

    @Override
    protected long store(Account bean) {

        if(bean.getId() != null) {
            Log.i(this.getClass().getName(), "Update existing account, id: " + bean.getId());
            updateById(bean);
            return bean.getId();
        } else {
            ContentValues values = new ContentValues();
            values.put(ACCOUNT_NAME, bean.getName());
            values.put(BASE_CURRENCY, bean.getBaseCurrency());
            values.put(TYPE, bean.getType());
            values.put(PARENT_ACCOUNT_ID, bean.getParentAccountId());
            values.put(FULL_NAME, bean.getFullName());
            values.put(DESCRIPTION, bean.getDescription());
            return db.insert(TABLE_NAME, null, values);
        }
    }

    @Override
    protected int updateById(Account bean) {
        ContentValues values = new ContentValues();
        if(bean.getName() != null) {
            values.put(ACCOUNT_NAME, bean.getName());
        }
        if(bean.getBaseCurrency() != null) {
            values.put(BASE_CURRENCY, bean.getBaseCurrency());
        }
        if(bean.getType() != null) {
            values.put(TYPE, bean.getType());
        }
        if(bean.getParentAccountId() != null) {
            values.put(PARENT_ACCOUNT_ID, bean.getParentAccountId());
        }
        if(bean.getFullName() != null) {
            values.put(FULL_NAME, bean.getFullName());
        }
        if(bean.getDescription() != null) {
            values.put(DESCRIPTION, bean.getDescription());
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
        int deleteCount = db.delete(TABLE_NAME, sb.toString(), null);
        transactionStore.deleteByAccountId(ids);
        return deleteCount;
    }

    @Override
    protected int deleteAll() {
        int deleteCount = db.delete(TABLE_NAME, null, null);
        transactionStore.deleteAll();
        return deleteCount;
    }

    @Override
    protected Account queryById(long id) {
        Cursor cursor = db.query(TABLE_NAME, null, ID + " = " + id, null, null, null, null, null);
        if(cursor == null) {
            return null;
        } else if(cursor.getCount() <= 0) {
            cursor.close();
            return null;
        } else {
            cursor.moveToFirst();
            Account account = buildAccount(cursor);
            cursor.close();
            return account;
        }
    }

    private Account buildAccount(Cursor cursor) {
        return new Account(cursor.getLong(ID_INDEX), cursor.getString(ACCOUNT_NAME_INDEX), cursor.getString(TYPE_INDEX),
                cursor.getString(BASE_CURRENCY_INDEX), cursor.getString(FULL_NAME_INDEX), cursor.getLong(PARENT_ACCOUNT_ID_INDEX),
                cursor.getString(DESCRIPTION_INDEX));
    }

    @Override
    protected List<Account> queryByIdOffset(Long offset, int size) {
        Cursor cursor = db.query(TABLE_NAME, null, ID + " < " + offset, null, null, null, ID + " DESC", String.valueOf(size));
        if(cursor == null) {
            return null;
        } else if(cursor.getCount() <= 0) {
            cursor.close();
            return null;
        } else {
            List<Account> list = new ArrayList<Account>(cursor.getCount());
            while(cursor.moveToNext()) {
                list.add(buildAccount(cursor));
            }
            cursor.close();
            return list;
        }
    }
}
