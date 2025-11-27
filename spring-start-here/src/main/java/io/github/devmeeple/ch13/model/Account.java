package io.github.devmeeple.ch13.model;

import java.math.BigDecimal;

public class Account {

    private long id;
    private String name;
    private BigDecimal amount;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
