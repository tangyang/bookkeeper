package com.weibo.bookkeeper.model;

/**
 * Created by HuangXiao on 14-8-10.
 */
public class Transaction {

    private long id;

    private String name;

    private String type;

    private double amount;

    private String currencyCode;

    private String description;

    private long createTime;

    private long accountId;

    public Transaction(long id, String name, String type, double amount, String currencyCode, String description, long createTime, long accountId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.description = description;
        this.createTime = createTime;
        this.accountId = accountId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
}
