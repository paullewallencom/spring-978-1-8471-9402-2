package it.freshfruits.domain.service;

import it.freshfruits.domain.entity.FruitType;
import it.freshfruits.domain.entity.FruitTypeImpl;
import it.freshfruits.domain.vo.OrderItem;
import it.freshfruits.domain.vo.OrderItemImpl;
import it.freshfruits.domain.vo.QuantityAndItemVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplyServiceImpl implements SupplyService {

    public SupplyServiceImpl() {
        this.availableItems = new HashMap<String, QuantityAndItemVO>();
        this.reservedItems = new HashMap<String, List<OrderItem>>();
    }

    public void init() {
        FruitType fruit = new FruitTypeImpl.Builder("orange", new Integer(2), new BigDecimal("0.20")).build();
        OrderItem item = new OrderItemImpl.Builder(fruit, 400, "1").build();
        availableItems.put(item.getFruitType().getId().toString(), new QuantityAndItemVO(item));
        FruitType fruitTwo = new FruitTypeImpl.Builder("lemon", new Integer(3), new BigDecimal("0.15")).build();
        OrderItem itemTwo = new OrderItemImpl.Builder(fruitTwo, 350, "1").build();
        availableItems.put(itemTwo.getFruitType().getId().toString(), new QuantityAndItemVO(itemTwo));
    }

    public Map<String, QuantityAndItemVO> getItemsAvailable() {
        return availableItems;
    }

    public Map<String, List<OrderItem>> getReservedItems() {
        return reservedItems;
    }

    public Boolean isAvailable(OrderItem item) {
        return availableItems.containsKey(item.getFruitType().getId().toString());
    }

    public Boolean release(String idOrder, String idItem) {
        Boolean result = false;

        List<OrderItem> listItems = reservedItems.get(idOrder);

        if (listItems != null && listItems.size() > 0) {

            for (int index = 0; index < listItems.size(); index++) {

                OrderItem item = listItems.get(index);
                if (item.getFruitType().getId().toString().equals(idItem)) {
                    listItems.remove(item);
                    QuantityAndItemVO qat = availableItems.get(idItem);
                    qat.add(item.getQuantity());
                    availableItems.put(idItem, qat);
                    reservedItems.put(idOrder, listItems);
                    result = true;
                }
            }
        }
        return result;
    }

    public Boolean retainItem(OrderItem item) {

        Boolean result = false;

        QuantityAndItemVO qat = availableItems.get(item.getFruitType().getId().toString());

        if (qat != null) {

            if (qat.getQuantity() >= item.getQuantity()) {

                List<OrderItem> items = reservedItems.get(item.getIdOrder().toString());
                if (items == null) {
                    items = new ArrayList<OrderItem>();
                }
                items.add(item);
                reservedItems.put(item.getIdOrder().toString(), items);
                qat.setQuantity(qat.getQuantity() - item.getQuantity());
                availableItems.put(qat.getItem().getFruitType().getId().toString(), qat);
                result = true;
            }
        }
        return result;
    }

    private Map<String, QuantityAndItemVO> availableItems;
    private Map<String, List<OrderItem>> reservedItems;
}
