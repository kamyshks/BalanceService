package kamyshks.repository;

import kamyshks.entity.BalanceEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BalanceRepository extends CrudRepository<BalanceEntity, Integer> {

    Optional<BalanceEntity> findById(Integer id);
}
