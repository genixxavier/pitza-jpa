package com.gnxcode.pitzza.persitence.entity.audit;

import com.gnxcode.pitzza.persitence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {
    private PizzaEntity currentValue;

    @PostLoad
    public void postLoad(PizzaEntity entity){
        System.out.println("Post load");
        currentValue = SerializationUtils.clone(entity);
    }

    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity entity){
        System.out.println("Post persist or update");
        System.out.println("Old value: " + currentValue.toString());
        System.out.println("New Value:" + entity.toString());
    }

    @PreRemove
    public void onPreDelete(PizzaEntity entity){
        System.out.println(entity.toString());
    }
}
