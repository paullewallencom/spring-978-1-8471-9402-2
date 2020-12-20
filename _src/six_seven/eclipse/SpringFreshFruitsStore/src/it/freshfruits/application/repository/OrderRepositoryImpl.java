package it.freshfruits.application.repository;

import it.freshfruits.domain.entity.Order;
import it.freshfruits.domain.entity.OrderImpl;
import it.freshfruits.domain.vo.OrderItem;
import it.freshfruits.domain.vo.OrderItemMap;
import it.freshfruits.domain.vo.OrderMap;
import it.freshfruits.exception.OrderItemsException;
import it.freshfruits.util.Constants;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapExecutor;

@Repository("orderRepository")
public class OrderRepositoryImpl extends SqlMapClientTemplate implements OrderRepository {

    public Boolean saveOrder(Order order) {
        if (order.getId().toString().equals(Constants.ID_NEW)) {
            return !insertOrder(order).equals(null) ? true : false;
        } else {
            return updateOrder(order);
        }
    }

    public Order getOrder(String id, String idCustomer) {
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("id", Integer.valueOf(id));
        params.put("idCustomer", Integer.valueOf(idCustomer));
        OrderMap dto = (OrderMap) queryForObject("selectOrderVO", params);
        return new OrderImpl.Builder(dto.getId().toString(), dto.getDateOrder(), dto.getIdCustomer().toString()).orderItems(dto.getOrderItems()).build();
    }

    @SuppressWarnings("unchecked")
    public List<Order> getOrders(String idCustomer) {
        return queryForList("selectOrdersVO", Integer.valueOf(idCustomer));
    }

    public String insertOrder(Order order) throws OrderItemsException {
        Integer idOrder = (Integer) insert("insertOrderVO", new OrderMap(order));
        if (order.getNumberItems() > 0) {
            int result = insertOrderItems(order.getOrderItems(), idOrder);
            if (result != order.getNumberItems()) {
                throw new OrderItemsException("some items not insert");
            }
        }
        return idOrder.toString();
    }

    private Integer insertOrderItems(final Set<OrderItem> items, final Integer idOrder) {
        return (Integer) execute(new SqlMapClientCallback() {
            public Object doInSqlMapClient(SqlMapExecutor executor) {
                int ris = 0;
                try {
                    executor.startBatch();
                    Iterator<OrderItem> iter = items.iterator();

                    while (iter.hasNext()) {
                        OrderItem item = iter.next();
                        OrderItemMap map = new OrderItemMap();
                        map.setFruit(item.getFruitType());
                        map.setIdOrder(idOrder);
                        map.setQuantity(item.getQuantity());
                        executor.insert("insertOrderItemVO", map);
                    }
                    ris = executor.executeBatch();
                } catch (SQLException e) {
                    Logger log = Logger.getLogger(this.getClass());
                    StringBuffer sb = new StringBuffer("insertOrderItem failed \n").append("num items batch:").append(items.size()).append("\n").append(e.getNextException());
                    log.error(sb.toString());
                    ;
                }
                return ris;
            }
        });
    }

    public Boolean updateOrder(Order order) {
        return update("updateOrderVO", new OrderMap(order)) == 1 ? true : false;
    }

    @Autowired @Override
    public void setSqlMapClient(@Qualifier("sqlMapClient") SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }
}
