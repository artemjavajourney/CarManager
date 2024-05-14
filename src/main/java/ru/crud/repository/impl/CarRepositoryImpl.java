package ru.crud.repository.impl;

import org.apache.commons.lang3.StringUtils;
import ru.crud.db.DatabaseConnectionManager;
import ru.crud.entity.CarEntity;
import ru.crud.mappers.CarMapper;
import ru.crud.repository.api.CarRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс работает с БД таблицей автомобиль
 * Created by vasilev-ad on 11.05.24
 */
public class CarRepositoryImpl implements CarRepository {

    private static final CarMapper MAPPER = new CarMapper();
    private static final String FIND_BY_ID = "SELECT * FROM cars WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM cars";
    private static final String INSERT = "INSERT INTO cars (brand, model, color, price) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE cars SET brand = ?, model = ?, color = ?, price = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM cars WHERE id = ?";

    @Override
    public CarEntity findById(Long carId) {
        System.out.println("SQL запрос: " + FIND_BY_ID);
        CarEntity entity = null;
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stm = createStatement(con, FIND_BY_ID, carId);
             ResultSet rs = stm.executeQuery()) {

            if (rs.next()) {
                entity = MAPPER.mapRow(rs);
                System.out.println("Получено из БД: " + entity);
            }

        } catch (Exception e) {
            System.out.println("Ошибка обращения к БД: " + e.getMessage());
        }
        return entity;
    }

    @Override
    public List<CarEntity> findAll() {
        System.out.println("SQL запрос: " + FIND_ALL);
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stm = createStatement(con, FIND_ALL);
             ResultSet rs = stm.executeQuery()) {
            List<CarEntity> cars = new ArrayList<>();
            while (rs.next()) {
                cars.add(MAPPER.mapRow(rs));
            }
            System.out.println("Получено из БД: " + cars);
            return cars;
        } catch (Exception e) {
            System.out.println("Ошибка обращения к БД: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Long insert(CarEntity car) {
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stm = createStatement(con, INSERT, car.getBrand(),
                     car.getModel(), car.getColor(), String.valueOf(car.getPrice()))) {
            int affectedRows = stm.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        System.out.println("Вставка прошла успешна: " + car);
                        return generatedKeys.getLong(1);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка обращения к БД: " + e.getMessage());
            throw new RuntimeException("Не удалось добавить автомобиль");
        }
        return null;
    }

    @Override
    public Boolean update(CarEntity car) {
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stm = createStatement(con, UPDATE, car.getBrand(),
                     car.getModel(), car.getColor(), String.valueOf(car.getPrice()), car.getId())) {
            stm.executeUpdate();
            System.out.println("Запись была успешна обновлена: " + car);
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка обращения к БД: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean remove(Long carId) {
        System.out.println("SQL запрос: " + DELETE);
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stm = createStatement(con, DELETE, carId)) {
            stm.executeUpdate();
            System.out.println("Удаление машины с id " + carId + " прошло успешно");
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка обращения к БД: " + e.getMessage());
            return false;
        }
    }

    public PreparedStatement createStatement(Connection connection, String query, Object... carFields) {
        try {
            PreparedStatement stm = StringUtils.startsWith(query, "INSERT")
                    ? connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
                    : connection.prepareStatement(query);

            for (int i = 0; i < carFields.length; i++) {
                if (carFields[i] instanceof Long) {
                    stm.setLong(i + 1, (Long) carFields[i]);
                } else if (carFields[i] instanceof String) {
                    stm.setString(i + 1, (String) carFields[i]);
                }
            }
            return stm;
        } catch (Exception e) {
            System.out.println("Ошибка доступа к БД: " + e.getMessage());
            throw new RuntimeException("Ошибка доступа к БД");
        }
    }
}
