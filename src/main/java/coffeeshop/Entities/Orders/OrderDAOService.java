package coffeeshop.Entities.Orders;


public class OrderDAOService {
    private static OrderDAO orderDAO = new OrderDAO();

    private OrderDAOService(OrderDAO orderDAO){
        OrderDAOService.orderDAO = orderDAO;
    }

    public void saveOrder(Order order){
        orderDAO.save(order);
    }
}
