package ru.crud.entity;

import java.math.BigDecimal;

/**
 * Класс описывает сущность машины
 * Created by vasilev-ad on 11.05.24
 */
public class Car {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Бренд
     */
    private String brand;

    /**
     * Модель
     */
    private String model;

    /**
     * Цвет
     */
    private String color;

    /**
     * Цена
     */
    private BigDecimal price;

    public Car() {}

    public Car(Long id, String brand, String model, String color, BigDecimal price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}
