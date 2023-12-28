package coffeeshop.Entities.Orders;

import java.util.List;

public class OrderDAOService {
    private static OrderDAO orderDAO = new OrderDAO();

    private OrderDAOService(OrderDAO orderDAO){
        OrderDAOService.orderDAO = orderDAO;
    }

    public static void saveOrder(Order order){
        orderDAO.save(order);
    }

    public static int getMostRecentOrderID() {
        return OrderDAO.getMostRecentOrderID();
    }

    public static List<Order> getAllOrdersForCustomer(int customerID) {
        return OrderDAOService.getAllOrdersForCustomer(customerID);
    }

}
