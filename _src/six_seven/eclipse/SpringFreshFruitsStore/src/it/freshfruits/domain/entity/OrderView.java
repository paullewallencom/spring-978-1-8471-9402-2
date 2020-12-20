package it.freshfruits.domain.entity;

import it.freshfruits.domain.vo.OrderItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public interface OrderView {

    public Integer getId();

    public Integer getIdCustomer();

    public String getStatus();

    public BigDecimal getAmount();

    public Set<OrderItem> getOrderItems();

    public Date getDateOrder();

}