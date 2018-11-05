package ua.com.owu.service.eventService;


import ua.com.owu.models.Event;


public interface EventService {
    Event findByEventId(int id);
}
