package com.gnxcode.pitzza.service.dto;

import java.util.Objects;

public class RandomOrderDto {
    private String idCustomer;
    private String method;

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RandomOrderDto that = (RandomOrderDto) o;
        return Objects.equals(idCustomer, that.idCustomer) && Objects.equals(method, that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer, method);
    }

    @Override
    public String toString() {
        return "RandomOrderDto{" +
                "idCustomer='" + idCustomer + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
