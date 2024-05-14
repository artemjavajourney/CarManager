package ru.crud.controller;

import ru.crud.repository.impl.CarRepositoryImpl;
import ru.crud.service.CarService;
import ru.crud.utils.ResponseUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/car")
@Produces("application/json;charset=UTF-8")
@Consumes("application/json;charset=UTF-8")
public class CarController {

    private final CarService service = new CarService(new CarRepositoryImpl());

    @GET
    @Path("/find/all")
    public Response findAll() {
        return ResponseUtils.execute(() -> ResponseUtils.listResponse(service.findAll()));
    }

    @GET
    @Path("/findById")
    public Response findById(@QueryParam("carId") Long carId) {
        return ResponseUtils.execute(() -> ResponseUtils.objectResponse(service.findById(carId)));
    }

    @POST
    @Path("/insert")
    public Response insert(@QueryParam("brand") String brand,
                           @QueryParam("model") String model,
                           @QueryParam("color") String color,
                           @QueryParam("price") String price) {
        return ResponseUtils.execute(() -> ResponseUtils.insertResponse(service.insert(brand, model, color, price)));
    }

    @POST
    @Path("/update")
    public Response update(@QueryParam("carId") Long carId,
                           @QueryParam("brand") String brand,
                           @QueryParam("model") String model,
                           @QueryParam("color") String color,
                           @QueryParam("price") String price) {
        return ResponseUtils.execute(() -> ResponseUtils.booleanResponse(service.update(carId, brand, model, color, price)));
    }

    @POST
    @Path("/delete")
    public Response delete(@QueryParam("carId") Long carId) {
        return ResponseUtils.execute(() -> ResponseUtils.booleanResponse(service.remove(carId)));
    }
}
