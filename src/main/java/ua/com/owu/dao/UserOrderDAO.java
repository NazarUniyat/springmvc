package ua.com.owu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.models.UserOrder;

@Repository
public interface UserOrderDAO extends JpaRepository<UserOrder,Integer> {
    UserOrder findByOrderId(int orderID);
}
