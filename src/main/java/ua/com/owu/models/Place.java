package ua.com.owu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int placeId;
    private String name;
    private String address;
    private String city;
    private String specification;
    private String about;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "place")
    private List<Manager> managers;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "place")
    private List<PlaceTable> placeTables;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY ,mappedBy = "place")
    private List<Event> events;




    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    public List<PlaceTable> getPlaceTables() {
        return placeTables;
    }

    public void setPlaceTables(List<PlaceTable> placeTables) {
        this.placeTables = placeTables;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void addManager(Manager manager){
        managers.add(manager);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return placeId == place.placeId &&
                Objects.equals(name, place.name) &&
                Objects.equals(address, place.address) &&
                Objects.equals(city, place.city) &&
                Objects.equals(specification, place.specification) &&
                Objects.equals(about, place.about) &&
                Objects.equals(managers, place.managers) &&
                Objects.equals(placeTables, place.placeTables) &&
                Objects.equals(events, place.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeId, name, address, city, specification, about, managers, placeTables, events);
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeId=" + placeId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", specification='" + specification + '\'' +
                '}';
    }
}
