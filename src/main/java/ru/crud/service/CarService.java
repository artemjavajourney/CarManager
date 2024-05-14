package ru.crud.service;

import ru.crud.entity.CarEntity;
import ru.crud.parser.CarParser;
import ru.crud.repository.api.CarRepository;

import java.util.List;

/**
 * Сервисный слой автомобиля
 * Created by vasilev-ad on 11.05.24
 */
public class CarService {

    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    /**
     * @see CarRepository#findById(Long)
     */
    public CarEntity findById(Long carId) {
        return repository.findById(carId);
    }

    /**
     * @see CarRepository#findAll()
     */
    public List<CarEntity> findAll() {
        return repository.findAll();
    }

    /**
     * @see CarRepository#insert(CarEntity)
     */
    public long insert(String brand, String model, String color, String price) {
        return repository.insert(CarParser.parse(null, brand, model, color, price));
    }

    /**
     * @see CarRepository#update(CarEntity)
     */
    public boolean update(Long id, String brand, String model, String color, String price) {
        return repository.update(CarParser.parse(id, brand, model, color, price));
    }

    /**
     * @see CarRepository#remove(Long)
     */
    public boolean remove(Long carId) {
        return repository.remove(carId);
    }
}
