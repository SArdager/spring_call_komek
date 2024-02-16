package kz.kdlolymp.springcallkomek.repositories;

import kz.kdlolymp.springcallkomek.entity.KnowledgeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface KnowledgeTypeRepository extends JpaRepository<KnowledgeType, Integer> {

    KnowledgeType findById(int id);

    List<KnowledgeType> findAllByKnowledgeId(int knowledgeId);

}
