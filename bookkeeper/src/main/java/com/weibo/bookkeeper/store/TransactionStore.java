package com.weibo.bookkeeper.store;

/**
 * Created by HuangXiao on 14-8-10.
 */
public class TransactionStore {

    private static final String TABLE_NAME = "Transaction";

    private static final String ID = "_id";

    private static final String NAME = "name";

    private static final String TYPE = "type";

    private static final String AMOUNT = "amount";

    private static final String CURRENCY_CODE = "currency_code";

    private static final String DESCRIPTION = "description";

    private static final String CREATE_TIME = "create_time";

    private static final String ACCOUNT_ID = "account_id";

    static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME  + " ("
            + ID            + " integer primary key autoincrement, "
            + NAME                      + " varchar(255), "
            + TYPE                      + " varchar(255) not null, "
            + AMOUNT            + " varchar(255) not null, "
            + CURRENCY_CODE            + " varchar(255) not null, "
            + DESCRIPTION       + " text, "
            + CREATE_TIME         + " integer not null, "
            + ACCOUNT_ID       + " integer not null);";
}
