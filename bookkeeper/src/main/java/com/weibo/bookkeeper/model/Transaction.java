package com.weibo.bookkeeper.model;

/**
 * Created by HuangXiao on 14-8-10.
 */
public class Transaction {

    private Long id;

    private String name;

    private String type;

    private Double amount;

    private String currencyCode;

    private String description;

    private Long createTime;

    private Long accountId;

    public Transaction(Long id, String name, String type, Double amount, String currencyCode, String description, Long createTime, Long accountId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.description = description;
        this.createTime = createTime;
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
