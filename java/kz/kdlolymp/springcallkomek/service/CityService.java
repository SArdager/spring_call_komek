package kz.kdlolymp.springcallkomek.service;

import kz.kdlolymp.springcallkomek.entity.City;
import kz.kdlolymp.springcallkomek.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CityService {
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private CityRepository cityRepository;

    public List<City> getAll(){
        return cityRepository.findAll();
    }

}
