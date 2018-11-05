package ua.com.owu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue("manager")
public class Manager  extends Account {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private Place place;
    public Place getPlace() {
        return place;
    }
    public void setPlace(Place place) {
        this.place = place;
    }

    public Manager() {
    }
    public Manager(Role role, String password, String username, String email) {
        super(role, password, username, email);
    }

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "managers")
    private
    List<UserOrder> userOrders;
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
        Manager manager = (Manager) o;
        return Objects.equals(place, manager.place) &&
                Objects.equals(userOrders, manager.userOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(place, userOrders);
    }


}

