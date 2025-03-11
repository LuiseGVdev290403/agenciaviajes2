package com.agenciatorus.api.Controller.dashboard;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenciatorus.api.Services.ReservaServices;
import com.agenciatorus.api.Services.ToursServices;
import com.agenciatorus.api.Services.DTO.TourDto;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class dashboarController {

    @Autowired
    private ToursServices toursServices;

    @Autowired
    private ReservaServices reservaServices;

    @GetMapping("/")
    private Map<String, Object> mostrarTours() {
        return toursServices.mostrarTours();
    }
    @PostMapping("/agregar")
    private Map<String, Object> agregarTours (@RequestBody TourDto tourDto) {
        return toursServices.agregarTour(tourDto);
    }
    @GetMapping("/verTours")
    private Map<String, Object> verTours(){
        return toursServices.mostrarTours();
    }
    @GetMapping("/verReservas")
    private ResponseEntity<?> verReservas(){
        return reservaServices.verReserva();
    }
    @DeleteMapping("/eliminarTour/{id}")
    private ResponseEntity<?> eliminarReserva(@PathVariable Long id){
        return toursServices.eliminarTorus(id);
    }
}
