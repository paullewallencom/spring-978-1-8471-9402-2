package it.freshfruits.domain.entity;

import it.freshfruits.domain.vo.OrderItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public interface Order extends BaseEntity {

    public String getStatus();

    public String getIdCustomer();

    public Set<OrderItem> getOrderItems();

    public Integer getNumberItems();

    public boolean addOrderItem(OrderItem item);

    public void saveOrder();

    public BigDecimal getOrderAmount();

    public Date getDateOrder();

    public Boolean removeOrderItem(String idOrder, String idItem);
}
