package ru.crud.mappers;

import ru.crud.entity.CarEntity;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс описывает трансформацию данных из БД в сущность машины
 * Created by vasilev-ad on 11.05.24
 */
public class CarMapper implements RowMapper<CarEntity> {

    @Override
    public CarEntity mapRow(ResultSet rs) throws SQLException {
        CarEntity entity = new CarEntity();

        entity.setId(rs.getLong(1));
        entity.setBrand(rs.getString(2));
        entity.setModel(rs.getString(3));
        entity.setColor(rs.getString(4));
        entity.setPrice(new BigDecimal(rs.getString(5)));

        return entity;
    }
}
