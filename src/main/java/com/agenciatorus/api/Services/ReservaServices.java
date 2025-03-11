package com.agenciatorus.api.Services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.agenciatorus.api.Entities.Reservas;
import com.agenciatorus.api.Entities.Tours;
import com.agenciatorus.api.Repository.ReservaRepository;
import com.agenciatorus.api.Repository.ToursRepository;
import com.agenciatorus.api.Services.DTO.ReservasDto;

@Service
public class ReservaServices {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ToursRepository toursRepository;

    public ResponseEntity<?> reservasAgregar(ReservasDto reservasDto){

         // Verificar que el Tour existe
        Optional<Tours> tourOptional = toursRepository.findByTitle(reservasDto.getTour());

        if (!tourOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Tour con el ID proporcionado no existe.");
        }

        Tours tour = tourOptional.get();

        // Crear la entidad Reserva a partir del DTO
        Reservas reserva = new Reservas();
        reserva.setReservaId(reservasDto.getReservaId());
        reserva.setName(reservasDto.getName());
        reserva.setLastname(reservasDto.getLastname());
        reserva.setPhone(reservasDto.getPhone());
        reserva.setEmail(reservasDto.getEmail());
        reserva.setNumberPassengers(reservasDto.getNumberPassengers());
        reserva.setDate(reservasDto.getDate());
        reserva.setPagado(reservasDto.isPagado());
        reserva.setTour(tour); // Relacionar el Tour con la Reserva

        // Guardar la reserva
        reservaRepository.save(reserva);

        return ResponseEntity.status(HttpStatus.CREATED).body("Reserva registrada exitosamente.");
   
    }
public ResponseEntity<?> verReserva() {
    try {
        List<Map<String, Object>> reservasList = new ArrayList<>();

        for (Reservas reserva : reservaRepository.findAll()) {
            Map<String, Object> reservaMap = new HashMap<>();
            reservaMap.put("reservaId", reserva.getReservaId());
            reservaMap.put("name", reserva.getName());
            reservaMap.put("lastname", reserva.getLastname());
            reservaMap.put("phone", reserva.getPhone());
            reservaMap.put("email", reserva.getEmail());
            reservaMap.put("numberPassengers", reserva.getNumberPassengers());
            reservaMap.put("date", reserva.getDate());
            reservaMap.put("pagado", reserva.isPagado());

            // Mapeo del Tour
            if (reserva.getTour() != null) {
                Map<String, Object> tourMap = new HashMap<>();
                tourMap.put("id", reserva.getTour().getId());
                tourMap.put("title", reserva.getTour().getTitle());
                tourMap.put("location", reserva.getTour().getLocation());
                tourMap.put("image", reserva.getTour().getImage());
                tourMap.put("description", reserva.getTour().getDescription());
                tourMap.put("longDescription", reserva.getTour().getLongDesciption());

                // Mapeo del TourDetails
                if (reserva.getTour().getTourDetails() != null) {
                    Map<String, Object> detailsMap = new HashMap<>();
                    detailsMap.put("id", reserva.getTour().getTourDetails().getId());
                    detailsMap.put("maxPeople", reserva.getTour().getTourDetails().getMaxPeople());
                    detailsMap.put("durationDays", reserva.getTour().getTourDetails().getDurationDays());
                    detailsMap.put("priceAmount", reserva.getTour().getTourDetails().getPriceAmount());
                    detailsMap.put("priceCurrency", reserva.getTour().getTourDetails().getPriceCurrency());
                    detailsMap.put("category", reserva.getTour().getTourDetails().getCategory());

                    tourMap.put("tourDetails", detailsMap);
                }

                reservaMap.put("tour", tourMap);
            }

            reservasList.add(reservaMap);
        }

        return ResponseEntity.ok(reservasList);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Ha ocurrido un error al obtener las reservas", "detalle", e.getMessage()));
    }
}


}
