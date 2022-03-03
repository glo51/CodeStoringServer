package platform.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import platform.models.Code;

import java.util.List;


public interface CodeRepository extends CrudRepository<Code, String> {

    @Query(value = "SELECT * FROM Codes WHERE time <= 0 AND views <= 0 ORDER BY date DESC LIMIT 10",
            nativeQuery = true)
    List<Code> latestTenCodes();
}
