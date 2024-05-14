package ru.crud.parser;

import ru.crud.entity.CarEntity;

import java.math.BigDecimal;

/**
 * Парсер автомобиля
 * Created by vasilev-ad on 11.05.24
 */
public class CarParser {

    /**
     * Метод создает экземпляр класса автомобиля на основе передаваемых параметров
     * @param id    идентификатор
     * @param brand бренд
     * @param model модель
     * @param color цвет
     * @param price цена
     * @return экземпляр автомобиля
     * @see CarEntity
     */
    public static CarEntity parse(Long id, String brand, String model, String color, String price) {
        CarEntity entity = new CarEntity();

        entity.setId(id);
        entity.setBrand(brand);
        entity.setModel(model);
        entity.setColor(color);
        entity.setPrice(new BigDecimal(price));

        System.out.println(entity);
        return entity;
    }
}
