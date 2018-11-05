package ua.com.owu.service.eventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.EventDAO;
import ua.com.owu.models.Event;

@Transactional
@Service
public class EventServiceImpl implements EventService {


    @Autowired
    EventDAO eventDAO;
    @Override
    public Event findByEventId(int id) {
        return eventDAO.findByEventId(id);
    }
}
