package ua.com.owu.service.placeService;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.AccountDAO;
import ua.com.owu.dao.PlaceDAO;
import ua.com.owu.models.Event;
import ua.com.owu.models.Manager;
import ua.com.owu.models.Place;

import java.util.List;

@Service
@Transactional
public class PlaceServiceImp implements PlaceService {
    private final
    PlaceDAO placeDAO;
    private final AccountDAO accountDAO;

    @Autowired
    public PlaceServiceImp(PlaceDAO placeDAO, AccountDAO accountDAO) {
        this.placeDAO = placeDAO;
        this.accountDAO = accountDAO;
    }

    @Override
    public Place findByAddress(String address) {
        return placeDAO.findByAddress(address);
    }

    @Override
    public Place findByCity(String city) {
        return placeDAO.findByCity(city);
    }

    @Override
    public Place findByName(String name) {
        return placeDAO.findByName(name);
    }

    @Override
    public Place findByEvents(Event event) {
        return placeDAO.findByEvents(event);
    }

    @Override
    @Transactional
    public Place findById(int id) {
        return placeDAO.findOne(id);
    }

    @Override
    @Transactional
    public void save(Place place) {
        placeDAO.save(place);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        placeDAO.delete(id);
    }

    @Override
    @Transactional
    public void update(Manager manager, Place place) {
        Manager managerDB = (Manager) accountDAO.findByUsername(manager.getUsername());
        place.setPlaceId(managerDB.getPlace().getPlaceId());
        placeDAO.save(place);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public List<Place> findAll() {
        return placeDAO.findAll();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public Place findByManagerId(int id) {
        Manager manager = (Manager) accountDAO.findOne(id);
        Hibernate.initialize(manager.getPlace());
        return manager.getPlace();
    }


}
