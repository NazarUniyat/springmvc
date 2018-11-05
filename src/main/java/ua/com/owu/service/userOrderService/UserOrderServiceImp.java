package ua.com.owu.service.userOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.UserOrderDAO;
import ua.com.owu.models.UserOrder;

@Service
@Transactional
public class UserOrderServiceImp implements UserOrderService {
    private final UserOrderDAO userOrderDAO;

    @Autowired
    public UserOrderServiceImp(UserOrderDAO userOrderDAO) {
        this.userOrderDAO = userOrderDAO;
    }

    @Override
    public UserOrder findByOrderId(int orderID) {
        return userOrderDAO.findByOrderId(orderID);
    }
}
