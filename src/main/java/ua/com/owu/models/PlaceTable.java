package ua.com.owu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class PlaceTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tableId;
    private int capacity;/*кількість людей*/

    @JsonIgnore
    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "placeTable")
    private List<UserOrder> userOrders;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Place place;

    public PlaceTable() {
    }

    public PlaceTable(int capacity) {
        this.capacity = capacity;
    }

    public PlaceTable(int capacity, List<UserOrder> userOrders, Place place) {
        this.capacity = capacity;
        this.userOrders = userOrders;
        this.place = place;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceTable that = (PlaceTable) o;
        return tableId == that.tableId &&
                capacity == that.capacity &&
                Objects.equals(userOrders, that.userOrders) &&
                Objects.equals(place, that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, capacity, userOrders, place);
    }

    @Override
    public String toString() {
        return "PlaceTable{" +
                "tableId=" + tableId +
                ", capacity=" + capacity +
                '}';
    }
}
