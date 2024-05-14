package ru.crud.repository.api;

import ru.crud.entity.CarEntity;

import java.util.List;

/**
 * В классе происходит непосредственное взаимодействие с БД
 * Created by vasilev-ad on 11.05.24
 */
public interface CarRepository {

    /**
     * Метод получает машину из БД по конкретному идентификатору
     * @param carId id машины
     * @return Машину
     */
    CarEntity findById(Long carId);

    /**
     * Метод получает список всех машин из БД
     * @return список машин
     */
    List<CarEntity> findAll();

    /**
     * Метод сохраняет в БД новую запись
     * @param car сущность машины
     * @return id новой записи в БД
     */
    Long insert(CarEntity car);

    /**
     * Обновляет существующую запись в БД
     * @param car сущность машины
     * @return true - при успешном обновлении, false в остальных случаях
     */
    Boolean update(CarEntity car);

    /**
     * Метод удаляет запись из БД
     * @param carId id машины
     * @return true - при успешном удалении, false в остальных случаях
     */
    Boolean remove(Long carId);
}
