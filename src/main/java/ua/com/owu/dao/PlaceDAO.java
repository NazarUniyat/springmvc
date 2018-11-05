package ua.com.owu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.models.Event;
import ua.com.owu.models.Place;

@Repository
public interface PlaceDAO extends JpaRepository<Place,Integer> {
    Place findByAddress(String address);
    Place findByCity(String city);
    Place findByName(String name);
    Place findByEvents(Event event);
}
