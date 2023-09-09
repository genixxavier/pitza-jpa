package com.gnxcode.pitzza.persitence.entity;

import java.io.Serializable;
import java.util.Objects;

public class OrderItemId implements Serializable {
    private Integer idOrder;
    private Integer idItem;

    @Override
    public boolean equals(Object o){
        if (this == o) return  true;
        if (!(o instanceof OrderItemId that)) return false;
        return Objects.equals(idOrder, that.idOrder) && Objects.equals(idItem, that.idItem);
    }

    @Override
    public int hashCode(){ return Objects.hash(idOrder, idItem);}

    public OrderItemId() {
    }

    public OrderItemId(Integer idOrder, Integer idItem) {
        this.idOrder = idOrder;
        this.idItem = idItem;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }
}
