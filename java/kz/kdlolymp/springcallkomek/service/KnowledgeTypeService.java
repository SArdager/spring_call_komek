package kz.kdlolymp.springcallkomek.service;

import kz.kdlolymp.springcallkomek.entity.KnowledgeType;
import kz.kdlolymp.springcallkomek.repositories.KnowledgeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class KnowledgeTypeService {
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private KnowledgeTypeRepository typeRepository;

    public List<KnowledgeType> getTypesByKnowledgeId(int knowledgeId){
        return typeRepository.findAllByKnowledgeId(knowledgeId);
    }

}
