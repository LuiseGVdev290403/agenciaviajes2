package com.agenciatorus.api.Controller.frontend;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agenciatorus.api.Services.ReservaServices;
import com.agenciatorus.api.Services.ToursServices;
import com.agenciatorus.api.Services.DTO.ReservasDto;

@RestController
@CrossOrigin(origins = "*")
public class ToursController {
    @Autowired
    private ToursServices toursServices;
    @Autowired
    private ReservaServices reservaServices;
    @GetMapping("/")
    private Map<String, Object> mostrarTours() {
        return toursServices.mostrarTours();
    }
    @PostMapping("/agregar")
    private ResponseEntity<?> reservar(@RequestBody ReservasDto reservasDto){
        return reservaServices.reservasAgregar(reservasDto);
    }
    @GetMapping("/tourid/{id}")
    private  ResponseEntity<?> obtenerTour(@PathVariable Long id){
        return toursServices.obtenerTourId(id);
    }
    
}
