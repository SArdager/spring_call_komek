package kz.kdlolymp.springcallkomek.service;

import kz.kdlolymp.springcallkomek.entity.Knowledge;
import kz.kdlolymp.springcallkomek.repositories.KnowledgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class KnowledgeService {
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private KnowledgeRepository knowledgeRepository;

    public List<Knowledge> getAll(){
        return knowledgeRepository.findAll();
    }
}
