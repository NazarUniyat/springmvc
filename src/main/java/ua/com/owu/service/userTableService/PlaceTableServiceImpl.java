package ua.com.owu.service.userTableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.owu.dao.UserTableDAO;
import ua.com.owu.models.PlaceTable;

@Service
@Transactional
public class PlaceTableServiceImpl implements PlaceTableService {
    private final UserTableDAO userTableDAO;

    @Autowired
    public PlaceTableServiceImpl(UserTableDAO userTableDAO) {
        this.userTableDAO = userTableDAO;
    }

    @Override
    public PlaceTable findByCapacity(int capacity) {
        return userTableDAO.findByCapacity(capacity);
    }

    @Override
    public PlaceTable findByTableId(int idTable) {
        return userTableDAO.findByTableId(idTable);
    }
}
