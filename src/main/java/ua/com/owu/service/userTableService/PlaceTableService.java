package ua.com.owu.service.userTableService;

import ua.com.owu.models.PlaceTable;

public interface PlaceTableService {
    PlaceTable findByCapacity(int capacity);
    PlaceTable findByTableId(int idTable);
}
