package coffeeshop.Entities.Orders;

import java.util.List;

import coffeeshop.Entities.Items.Item;

public class OrderDAOService {
    private static OrderDAO orderDAO = new OrderDAO();

    private OrderDAOService(OrderDAO orderDAO){
        OrderDAOService.orderDAO = orderDAO;
    }

    public static void saveOrder(Order order){
        orderDAO.save(order);
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

}
