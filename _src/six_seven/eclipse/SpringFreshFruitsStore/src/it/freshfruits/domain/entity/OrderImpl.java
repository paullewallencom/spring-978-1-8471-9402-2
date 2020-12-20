package it.freshfruits.domain.entity;

import it.freshfruits.application.repository.OrderRepository;
import it.freshfruits.domain.service.SupplyService;
import it.freshfruits.domain.vo.OrderItem;
import it.freshfruits.util.Constants;
import it.freshfruits.util.ValidationUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(dependencyCheck = true)
public class OrderImpl implements Order, Serializable {

    public String getIdCustomer() {
        return idCustomer.toString();
    }

    public void saveOrder() {
        orderRepository.saveOrder(this);
    }

    public Integer getNumberItems() {
        return orderItems.size();
    }

    public String getStatus() {
        return status;
    }

    public boolean addOrderItem(OrderItem item) {
        return supplyService.isAvailable(item) ? orderItems.add(item) : false;
    }

    public Date getDateOrder() {
        return new Date(dateOrder.getTime());
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public BigDecimal getOrderAmount() {
        if ((amount.compareTo(BigDecimal.ZERO) == 0) && orderItems.size() != 0) {

            for (OrderItem item : orderItems) {
                amount = amount.add(item.getAmountItem());
            }
        }
        return amount;
    }

    public Boolean removeOrderItem(String idOrder, String idItem) {
        Boolean result = false;
        for (OrderItem item : orderItems) {
            if (item.getFruitType().getId().toString().equals(idItem)) {
                result = orderItems.remove(item);
                if (result)
                    supplyService.release(idOrder, idItem);
                continue;
            }
        }
        return result;
    }

    public Integer getId() {
        return id;
    }

    public String toString() {

        StringBuilder sb = new StringBuilder().append("\nid:").append(id).append("\nidCustomer:").append(idCustomer).append("\ndateOrder:").append(dateOrder).append("\namount:").append(amount);

        if (orderItems != null && orderItems.size() > 0) {
            sb.append("\n");
            for (OrderItem item : orderItems) {
                sb.append(item);
            }
        }
        return sb.toString();
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setSupplyService(SupplyService supplyService) {
        this.supplyService = supplyService;
    }

    public static class Builder {
        // Required parameters
        private Integer id, idCustomer;
        private Date dateOrder;

        // Optional parameters
        private BigDecimal amount = new BigDecimal("0");
        private Set<OrderItem> orderItems = new HashSet<OrderItem>();

        public Builder(String id, Date dateOrder, String idCustomer) {
            ValidationUtils.validateId(id);
            ValidationUtils.validateId(idCustomer);
            ValidationUtils.validateDate(dateOrder);
            this.dateOrder = dateOrder;
            this.id = Integer.valueOf(id);
            this.idCustomer = Integer.valueOf(idCustomer);
        }

        public Builder amount(BigDecimal val) {
            ValidationUtils.validateAmount(val);
            amount = val;
            return this;
        }

        public Builder orderItems(Set<OrderItem> values) {
            orderItems = values;
            return this;
        }

        public OrderImpl build() {
            return new OrderImpl(this);
        }
    }

    private OrderImpl(Builder builder) {
        id = builder.id;
        idCustomer = builder.idCustomer;
        dateOrder = builder.dateOrder;
        amount = builder.amount;
        orderItems = builder.orderItems;
    }

    private Integer id, idCustomer;
    private String status = Constants.ORDER_NEW;
    private BigDecimal amount;
    private Set<OrderItem> orderItems;
    private Date dateOrder;
    private OrderRepository orderRepository;
    private SupplyService supplyService;
    private static final long serialVersionUID = 2525105011114628958L;
}
