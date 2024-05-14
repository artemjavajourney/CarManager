package ru.crud.utils;

import ru.crud.config.Config;

/**
 * Утильный класс для получения параметра
 * Created by vasilev-ad on 11.05.24
 */
public class PropertyUtils {

    private PropertyUtils() {
    }

    /**
     * Метод для получения значения параметра по его имени
     * @param name имя параметра
     * @return значения параметра
     */
    public static String getProperty(String name) {
        return Config.getInstance().getProperty(name);
    }
}
