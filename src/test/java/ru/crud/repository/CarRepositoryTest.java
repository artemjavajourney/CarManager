package ru.crud.repository;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import ru.crud.db.DatabaseConnectionManager;
import ru.crud.entity.CarEntity;
import ru.crud.repository.api.CarRepository;
import ru.crud.repository.impl.CarRepositoryImpl;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by vasilev-ad on 11.05.24
 */
public class CarRepositoryTest {

    private final CarRepository carRepository = new CarRepositoryImpl();

    @After
    public void tearDown() {
        try {
            Field field = DatabaseConnectionManager.class.getDeclaredField("dbIsInit");
            field.setAccessible(true);
            field.setBoolean(DatabaseConnectionManager.class, false);
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void insertTest() {
        CarEntity car = new CarEntity();
        car.setBrand("BMW");
        car.setModel("2106");
        car.setColor("Серо-буро-малиновый");
        car.setPrice(BigDecimal.valueOf(666.66));
        carRepository.insert(car);

        CarEntity result = carRepository.findById(1L);
        Assert.assertNotNull(result);
        Assert.assertEquals(Long.valueOf(1), result.getId());
        Assert.assertEquals(car.getBrand(), result.getBrand());
        Assert.assertEquals(car.getModel(), result.getModel());
        Assert.assertEquals(car.getColor(), result.getColor());
        Assert.assertEquals(car.getPrice(), result.getPrice());
    }

    @Test
    public void updateTest() {
        insertTest();
        CarEntity car = new CarEntity();
        car.setId(1L);
        car.setBrand("BMW");
        car.setModel("2106");
        car.setColor("Серо-буро-малиновый");
        car.setPrice(BigDecimal.valueOf(777.77));
        carRepository.update(car);

        CarEntity result = carRepository.findById(1L);
        Assert.assertNotNull(result);
        Assert.assertEquals(Long.valueOf(1), result.getId());
        Assert.assertEquals(car.getBrand(), result.getBrand());
        Assert.assertEquals(car.getModel(), result.getModel());
        Assert.assertEquals(car.getColor(), result.getColor());
        Assert.assertEquals(car.getPrice(), result.getPrice());
    }

    @Test
    public void deleteTest() {
        insertTest();

        carRepository.remove(1L);
        CarEntity result = carRepository.findById(1L);
        Assert.assertNull(result);
    }

    @Test
    public void findAllTest() {
        insertTest();
        insertTest();

        List<CarEntity> carEntities = carRepository.findAll();
        Assert.assertTrue(CollectionUtils.isNotEmpty(carEntities));
        Assert.assertEquals(2, carEntities.size());
    }
}
