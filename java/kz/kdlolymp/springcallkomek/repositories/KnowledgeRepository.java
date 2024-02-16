package kz.kdlolymp.springcallkomek.repositories;

import kz.kdlolymp.springcallkomek.entity.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface KnowledgeRepository extends JpaRepository<Knowledge, Integer> {
    Knowledge findById(int id);

    List<Knowledge> findAll();
}
