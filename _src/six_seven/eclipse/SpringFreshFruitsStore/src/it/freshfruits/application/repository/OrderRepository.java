package it.freshfruits.application.repository;

import it.freshfruits.domain.entity.Order;
import it.freshfruits.exception.OrderItemsException;

import java.util.List;

public interface OrderRepository {

    public String insertOrder(Order order) throws OrderItemsException;

    public Boolean saveOrder(Order order);

    public Boolean updateOrder(Order order);

    public Order getOrder(String id, String idCustomer);

    public List<Order> getOrders(String idCustomer);
}
