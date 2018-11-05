package ua.com.owu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

//@JsonIgnoreProperties("userOrders")
@Entity
@DiscriminatorValue("user")
public class User extends Account {

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserOrder> userOrders; // G S

    public User() {
    }
    public User(Role role, String password, String username, String email) {
        super(role, password, username, email);
    }

    public List<UserOrder> getUserOrders() {
        return userOrders;
    }
    public void setUserOrders(List<UserOrder> userOrders) {
        this.userOrders = userOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(userOrders, user.userOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userOrders);
    }
}
