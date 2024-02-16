package kz.kdlolymp.springcallkomek.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name="cabinets")
public class Cabinet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cabinet_id")
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "city_id")
    private City city;
    @Column(name = "cabinet_name")
    private String cabinetName;
    @Column(name = "cabinet_address")
    private String cabinetAddress;
    @Column(name = "cabinet_work_time")
    private String cabinetWorkTime;
    @Column(name = "cabinet_description")
    private String cabinetDescription;
    @Column(name = "cabinet_prick")
    private String cabinetPrick;
    @Column(name = "cabinet_covid")
    private String cabinetCovid;
    @Column(name = "transport")
    private String transport;
    @Column(name = "is_children")
    private boolean isChildrenService;
    @Column(name = "is_covid")
    private boolean isCovidService;
    @Column(name = "is_ramp")
    private boolean isRampExist;
    @Column(name = "is_injection")
    private boolean isInjectionService;
    @Column(name = "is_smear")
    private boolean isSmearService;
    @Column(name = "is_additional")
    private boolean isAdditionalService;
    @Column(name = "is_discount")
    private boolean isDiscount;
    @Column(name = "is_card_pay")
    private boolean isCardPay;

    public Cabinet() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getCity() {return city;}

    public void setCity(City city) {this.city = city;}

    public String getCabinetName() { return cabinetName; }

    public void setCabinetName(String cabinetName) { this.cabinetName = cabinetName; }

    public String getCabinetAddress() { return cabinetAddress; }

    public void setCabinetAddress(String cabinetAddress) { this.cabinetAddress = cabinetAddress; }

    public String getCabinetWorkTime() { return cabinetWorkTime; }

    public void setCabinetWorkTime(String cabinetWorkTime) { this.cabinetWorkTime = cabinetWorkTime; }

    public String getCabinetDescription() { return cabinetDescription; }

    public void setCabinetDescription(String cabinetDescription) { this.cabinetDescription = cabinetDescription; }

    public String getCabinetPrick() { return cabinetPrick; }

    public void setCabinetPrick(String cabinetPrick) { this.cabinetPrick = cabinetPrick; }

    public String getCabinetCovid() { return cabinetCovid; }

    public void setCabinetCovid(String cabinetCovid) { this.cabinetCovid = cabinetCovid; }

    public String getTransport() { return transport; }

    public void setTransport(String transport) { this.transport = transport; }

    public boolean isChildrenService() { return isChildrenService; }

    public void setChildrenService(boolean childrenService) { isChildrenService = childrenService; }

    public boolean isCovidService() { return isCovidService; }

    public void setCovidService(boolean covidService) { isCovidService = covidService; }

    public boolean isRampExist() { return isRampExist; }

    public void setRampExist(boolean rampExist) { isRampExist = rampExist; }

    public boolean isInjectionService() { return isInjectionService; }

    public void setInjectionService(boolean injectionService) { isInjectionService = injectionService; }

    public boolean isSmearService() { return isSmearService; }

    public void setSmearService(boolean smearService) { isSmearService = smearService; }

    public boolean isAdditionalService() { return isAdditionalService; }

    public void setAdditionalService(boolean additionalService) { isAdditionalService = additionalService; }

    public boolean isDiscount() { return isDiscount; }

    public void setDiscount(boolean discount) { isDiscount = discount; }

    public boolean isCardPay() { return isCardPay; }

    public void setCardPay(boolean cardPay) { isCardPay = cardPay; }
}
