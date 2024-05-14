package ru.crud.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.crud.entity.CarEntity;
import ru.crud.repository.api.CarRepository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created by vasilev-ad on 11.05.24
 */
@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @Mock
    CarRepository repository;

    @InjectMocks
    CarService carService;

    @Test
    public void findByIdTest() {
        CarEntity car = getEntity();
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(car);

        CarEntity result = carService.findById(1L);
        Assert.assertNotNull(result);
        Assert.assertEquals(Long.valueOf(1), result.getId());
        Assert.assertEquals(car.getBrand(), result.getBrand());
        Assert.assertEquals(car.getModel(), result.getModel());
        Assert.assertEquals(car.getColor(), result.getColor());
        Assert.assertEquals(car.getPrice(), result.getPrice());
    }

    @Test
    public void findAllTest() {
        CarEntity car = getEntity();
        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(car));

        List<CarEntity> result = carService.findAll();
        Assert.assertNotNull(result);
        Assert.assertEquals(Long.valueOf(1), result.get(0).getId());
        Assert.assertEquals(car.getBrand(), result.get(0).getBrand());
        Assert.assertEquals(car.getModel(), result.get(0).getModel());
        Assert.assertEquals(car.getColor(), result.get(0).getColor());
        Assert.assertEquals(car.getPrice(), result.get(0).getPrice());
    }

    @Test
    public void insertTest() {
        CarEntity car = getEntity();
        Mockito.when(repository.insert(Mockito.any(CarEntity.class))).thenReturn(car.getId());

        Long result = carService.insert(car.getBrand(), car.getModel(), car.getColor(), car.getPrice().toString());
        Assert.assertNotNull(result);
        Assert.assertEquals(Long.valueOf(1), result);
    }

    @Test
    public void updateTest() {
        CarEntity car = getEntity();
        Mockito.when(repository.update(Mockito.any(CarEntity.class))).thenReturn(true);

        Boolean result = carService.update(car.getId(), car.getBrand(), car.getModel(), car.getColor(), car.getPrice().toString());
        Assert.assertNotNull(result);
        Assert.assertTrue(result);
    }

    @Test
    public void delete() {
        Mockito.when(repository.remove(Mockito.anyLong())).thenReturn(true);

        Boolean result = repository.remove(1L);
        Assert.assertNotNull(result);
        Assert.assertTrue(result);
    }

    private CarEntity getEntity() {
        CarEntity car = new CarEntity();
        car.setId(1L);
        car.setBrand("BMW");
        car.setModel("2106");
        car.setColor("Серо-буро-малиновый");
        car.setPrice(BigDecimal.valueOf(666.66));
        return car;
    }
}
