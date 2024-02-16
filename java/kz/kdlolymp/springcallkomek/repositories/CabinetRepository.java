package kz.kdlolymp.springcallkomek.repositories;

import kz.kdlolymp.springcallkomek.entity.Cabinet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface CabinetRepository extends JpaRepository<Cabinet, Integer> {

    Cabinet findById(int id);

    List<Cabinet> findAllByCityId(int cityId);

}
