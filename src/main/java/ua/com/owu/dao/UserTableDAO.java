package ua.com.owu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.owu.models.PlaceTable;

@Repository
public interface UserTableDAO extends JpaRepository<PlaceTable,Integer> {
    PlaceTable findByCapacity(int capacity);
    PlaceTable findByTableId(int idTable);
}
