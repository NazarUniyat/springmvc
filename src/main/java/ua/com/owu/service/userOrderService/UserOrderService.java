package ua.com.owu.service.userOrderService;

import ua.com.owu.models.UserOrder;

public interface UserOrderService {
    UserOrder findByOrderId(int orderID);
}
