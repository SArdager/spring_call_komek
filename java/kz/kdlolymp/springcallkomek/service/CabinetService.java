package kz.kdlolymp.springcallkomek.service;

import kz.kdlolymp.springcallkomek.entity.Article;
import kz.kdlolymp.springcallkomek.entity.Cabinet;
import kz.kdlolymp.springcallkomek.repositories.CabinetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
public class CabinetService {
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private CabinetRepository cabinetRepository;

    public Cabinet getCabinetById(int id){
        return cabinetRepository.findById(id);
    }
    public List<Cabinet> getCabinetsByCity(int cityId) {
        return cabinetRepository.findAllByCityId(cityId);
    }

    public List<Cabinet> getCabinetsByParameters(int cityId, boolean covid, boolean children, boolean smear,
              boolean injection, boolean ramp, boolean additional, boolean discount, boolean cardPay) {
        String addSelect = "";
        if(covid){
            addSelect += " AND c.isCovidService = 'true'";
        }
        if(children){
            addSelect += " AND c.isChildrenService = 'true'";
        }
        if(smear){
            addSelect += " AND c.isSmearService = 'true'";
        }
        if(injection){
            addSelect += " AND c.isInjectionService = 'true'";
        }
        if(ramp){
            addSelect += " AND c.isRampExist = 'true'";
        }
        if(additional){
            addSelect += " AND c.isAdditionalService = 'true'";
        }
        if(discount){
            addSelect += " AND c.isDiscount = 'true'";
        }
        if(cardPay){
            addSelect += " AND c.isCardPay = 'true'";
        }
        List<Cabinet> cabinets = manager.createQuery("SELECT c FROM Cabinet c WHERE c.city.id = :paramCity " + addSelect,
                Cabinet.class).setParameter("paramCity", cityId).getResultList();

        return cabinets;
   }

}
