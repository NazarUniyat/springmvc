package ua.com.owu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.models.Event;

@Repository
public interface EventDAO extends JpaRepository<Event,Integer> {
    Event findByEventId(int id);
}
