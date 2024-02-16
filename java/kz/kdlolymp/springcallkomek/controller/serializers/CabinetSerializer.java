package kz.kdlolymp.springcallkomek.controller.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import kz.kdlolymp.springcallkomek.entity.Cabinet;

import java.lang.reflect.Type;

public class CabinetSerializer implements JsonSerializer<Cabinet> {
    @Override
    public JsonElement serialize(Cabinet cabinet, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jObject = new JsonObject();
        if(cabinet!=null) {
            jObject.addProperty("id", cabinet.getId());
            jObject.addProperty("cityId", cabinet.getCity().getId());
            jObject.addProperty("cityName", cabinet.getCity().getCityName());
            jObject.addProperty("cabinetName", cabinet.getCabinetName());
            jObject.addProperty("cabinetAddress", cabinet.getCabinetAddress());
            jObject.addProperty("cabinetWorkTime", cabinet.getCabinetWorkTime());
            jObject.addProperty("cabinetDescription", cabinet.getCabinetDescription());
            jObject.addProperty("cabinetPrick", cabinet.getCabinetPrick());
            jObject.addProperty("cabinetCovid", cabinet.getCabinetCovid());
            jObject.addProperty("transport", cabinet.getTransport());
            jObject.addProperty("isChildrenService", cabinet.isChildrenService());
            jObject.addProperty("isCovidService", cabinet.isCovidService());
            jObject.addProperty("isRampExist", cabinet.isRampExist());
            jObject.addProperty("isInjectionService", cabinet.isInjectionService());
            jObject.addProperty("isSmearService", cabinet.isSmearService());
            jObject.addProperty("isAdditionalService", cabinet.isAdditionalService());
            jObject.addProperty("isDiscount", cabinet.isDiscount());
            jObject.addProperty("isCardPay", cabinet.isCardPay());
        }
        return jObject;
    }
}
