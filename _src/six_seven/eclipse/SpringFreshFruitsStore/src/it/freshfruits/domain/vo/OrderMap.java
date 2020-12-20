package it.freshfruits.domain.vo;

import it.freshfruits.domain.entity.Order;
import it.freshfruits.domain.entity.OrderView;
import it.freshfruits.util.Constants;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class OrderMap implements OrderView {

    public OrderMap() {
    }

    public OrderMap(Order order) {

        this.dateOrder = order.getDateOrder();
        this.id = new Integer(order.getId());
        this.idCustomer = new Integer(order.getIdCustomer());
        this.orderItems = order.getOrderItems();
        this.amount = order.getOrderAmount();
        this.status = order.getStatus();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    private Integer id, idCustomer;
    private String status = Constants.ORDER_NEW;
    private BigDecimal amount;
    private Set<OrderItem> orderItems;
    private Date dateOrder;
}
