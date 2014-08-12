package com.weibo.bookkeeper.model;

/**
 * Created by HuangXiao on 14-8-10.
 */
public class Account {

    private Long id;

    private String name;

    private String type;

    private String baseCurrency;

    private String fullName;

    private Long parentAccountId;

    private String description;

    public Account(Long id, String name, String type, String baseCurrency, String fullName, Long parentAccountId, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.baseCurrency = baseCurrency;
        this.fullName = fullName;
        this.parentAccountId = parentAccountId;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setParentAccountId(Long parentAccountId) {
        this.parentAccountId = parentAccountId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getFullName() {
        return fullName;
    }

    public Long getParentAccountId() {
        return parentAccountId;
    }

    public String getDescription() {
        return description;
    }
}
