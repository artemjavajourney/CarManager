package ru.crud.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Интерфейс трансформирует данные из БД в необходимую сущность
 * Created by vasilev-ad on 11.05.24
 */
public interface RowMapper<T> {

    /**
     * Метод трансформирует данные из БД в необходимую сущность
     * @param rs результат, полученный из БД после запроса
     * @return возвращает необходимую сущность
     * @throws SQLException в случае ошибки получения данных из БД
     */
    T mapRow(ResultSet rs) throws SQLException;
}
