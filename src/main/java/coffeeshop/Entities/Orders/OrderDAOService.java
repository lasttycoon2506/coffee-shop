package coffeeshop.Entities.Orders;

import java.util.List;
import java.util.Optional;
import coffeeshop.Entities.Items.Item;


public class OrderDAOService {
    private static OrderDAO orderDAO = new OrderDAO();

    public static Order get(Integer orderId) {
        Optional<Order> order = orderDAO.get(orderId);
            return order.orElseGet(
            () -> {
                return null;
            });
    }
    
    public static int getMostRecentOrder() {
        return OrderDAO.getMostRecentOrder();
    }

    public static List<Order> getAllOrdersForCustomer(int customerID) {
        return OrderDAO.getAllOrdersForCustomer(customerID);
    }

    public static List<Item> getAllItemsForOrder(int orderID) {
        return OrderDAO.getAllItemsForOrder(orderID);
    }

    public static void saveOrder(Order order){
        orderDAO.save(order);
    }

    public static void editOrder(Order order){
        orderDAO.edit(order);
    }

    public static void deleteOrder(Order order){
        orderDAO.delete(order);
    }

}
