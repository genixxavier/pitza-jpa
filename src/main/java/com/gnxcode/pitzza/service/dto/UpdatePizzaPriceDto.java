package com.gnxcode.pitzza.service.dto;

import java.util.Objects;

public class UpdatePizzaPriceDto {
    private int pizzaId;
    private double newPrice;

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdatePizzaPriceDto that = (UpdatePizzaPriceDto) o;
        return pizzaId == that.pizzaId &&
                Double.compare(that.newPrice, newPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pizzaId, newPrice);
    }

    @Override
    public String toString() {
        return "UpdatePizzaPriceDto{" +
                "pizzaId=" + pizzaId +
                ", newPrice=" + newPrice +
                '}';
    }
}
