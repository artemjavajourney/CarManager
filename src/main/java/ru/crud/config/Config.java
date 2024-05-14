package ru.crud.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Конфиг
 * Created by vasilev-ad on 11.05.24
 */
public class Config {

    private Config() {}

    private static Properties properties;

    public static Properties getInstance() {
        if (properties == null || properties.isEmpty()) {
            synchronized (Config.class) {
                properties = new Properties(); // вопрос
                try (InputStream in = Config.class.getClassLoader()
                        .getResourceAsStream("dao.properties")) {
                    properties.load(in);
                } catch (IOException e) {
                    throw new RuntimeException("Ошибка при загрузки конфига");
                }
            }
        }
        return properties;
    }
}
