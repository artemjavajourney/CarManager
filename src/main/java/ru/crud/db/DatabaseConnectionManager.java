package ru.crud.db;

import ru.crud.utils.PropertyUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Класс создаем подключение к БД
 * Created by vasilev-ad on 11.05.24
 */
public class DatabaseConnectionManager {

    private static final String DB_USER = "db.login";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_URL = "db.url";
    private static boolean dbIsInit = false;

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(
                    PropertyUtils.getProperty(DB_URL),
                    PropertyUtils.getProperty(DB_USER),
                    PropertyUtils.getProperty(DB_PASSWORD));
            if (!dbIsInit) {
                initDb(connection);
                dbIsInit = true;
            }
            return connection;
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать соединение с БД: ", e);
        }
    }

    private static void initDb(Connection connection) {
        try {
            String createTableSQL = "DROP TABLE IF EXISTS cars;" +
                    "CREATE TABLE IF NOT EXISTS cars (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "brand VARCHAR(255)," +
                    "model VARCHAR(255)," +
                    "color VARCHAR(255)," +
                    "price VARCHAR(255))";
            PreparedStatement stm = connection.prepareStatement(createTableSQL);
            stm.executeUpdate();
            System.out.println("Инициализация БД прошла успешно");
        } catch (Exception e) {
            throw new RuntimeException("Не удалось инициализировать БД");
        }
    }
}
