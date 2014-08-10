package com.weibo.bookkeeper.store;

/**
 * Created by HuangXiao on 14-8-10.
 */
public class AccountStore {

    private static final String TABLE_NAME = "account";

    private static final String ID = "_id";

    private static final String ACCOUNT_NAME = "name";

    //本位币
    private static final String BASE_CURRENCY = "base_currency";

    private static final String TYPE = "type";

    private static final String PARENT_ACCOUNT_ID = "parent";

    private static final String FULL_NAME = "full_name";

    private static final String DESCRIPTION = "description";

    static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ("
            + ID + " integer primary key autoincrement, "
            + ACCOUNT_NAME      + " varchar(255) not null, "
            + TYPE      + " varchar(255) not null, "
            + BASE_CURRENCY + " varchar(255) not null, "
            + PARENT_ACCOUNT_ID + " integer, "
            + FULL_NAME + " varchar(255), "
            + DESCRIPTION + " varchar(2000));";
}
