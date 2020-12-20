package it.freshfruits.domain.service;

import it.freshfruits.domain.vo.OrderItem;
import it.freshfruits.domain.vo.QuantityAndItemVO;

import java.util.List;
import java.util.Map;

public interface SupplyService {

    public Boolean isAvailable(OrderItem item);

    public Boolean retainItem(OrderItem item);

    public Boolean release(String idOrder, String idItem);

    public Map<String, QuantityAndItemVO> getItemsAvailable();

    public void init();

    public Map<String, List<OrderItem>> getReservedItems();
}
