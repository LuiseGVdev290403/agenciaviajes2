package com.agenciatorus.api.Services;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.agenciatorus.api.Entities.Highlights;
import com.agenciatorus.api.Entities.Popularity;
import com.agenciatorus.api.Entities.Reviews;
import com.agenciatorus.api.Entities.TourDetails;
import com.agenciatorus.api.Entities.Tours;
import com.agenciatorus.api.Repository.HighlightsRepository;
import com.agenciatorus.api.Repository.PopularityRepository;
import com.agenciatorus.api.Repository.ReviewsRepository;
import com.agenciatorus.api.Repository.TourDetailsRepository;
import com.agenciatorus.api.Repository.ToursRepository;
import com.agenciatorus.api.Services.DTO.DetailsDto;
import com.agenciatorus.api.Services.DTO.TourDto;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ToursServices {
    @Autowired
    private ToursRepository toursRepository;

    @Autowired
    private TourDetailsRepository tourDetailsRepository;

    @Autowired
    private HighlightsRepository highlightsRepository;

    @Autowired
    private PopularityRepository popularityRepository;

    @Autowired
    private ReviewsRepository reviewsRepository;

    // mostrar solo tours
    public Map<String, Object> mostrarTours() {
        List<Map<String, Object>> tourList = toursRepository.findAll().stream().map(tour -> {
            Map<String, Object> tourDetails = new HashMap<>();
            // Buscar detalles del tour
            Optional<TourDetails> tourOptional = tourDetailsRepository.findDetailsByTourId(tour.getId());
            System.out.println("Buscando TourDetails con tour_id: " + tour.getId());

            String[] higlightsP = highlightsRepository.findDetailsByHighlightId(tour.getId()).toArray(new String[0]);
            List<Object[]> result = popularityRepository.findPopularityByTourId(tour.getId());

            if (!result.isEmpty()) {
                Object[] data = result.get(0); // Obtener la primera fila

                System.out.println("Contenido de data: " + Arrays.toString(data));

                if (data.length >= 2) {
                    Boolean isPopular = (data[0] instanceof Boolean) ? (Boolean) data[0] : (Integer) data[0] != 0;
                    String ranked = (String) data[1];

                    Map<String, Object> popularityData = new HashMap<>();
                    popularityData.put("is_popular", isPopular);
                    popularityData.put("rank", ranked);
                    tourDetails.put("popularity", popularityData);

                    System.out.println("Popularidad guardada: " + popularityData);
                }
            }

            List<Object[]> reviewsList = reviewsRepository.findReviewsByTourId(tour.getId());

            if (!reviewsList.isEmpty()) {
                Object[] reviewData = reviewsList.get(0); // Obtener la primera fila

                System.out.println("Contenido de reviewData: " + Arrays.toString(reviewData));

                if (reviewData.length >= 2) {
                    // Convertir el primer valor a Double y luego redondearlo a Integer si es
                    // necesario
                    Integer totalReviews = (reviewData[0] instanceof Number)
                            ? ((Number) reviewData[0]).intValue() // Evita errores de conversión
                            : Integer.valueOf(reviewData[0].toString().split("\\.")[0]); // Alternativa

                    // Convertir el segundo valor a Double
                    Double rating = (reviewData[1] instanceof Number)
                            ? ((Number) reviewData[1]).doubleValue()
                            : Double.valueOf(reviewData[1].toString());

                    // Guardar los datos en un mapa
                    Map<String, Object> reviewsData = new HashMap<>();
                    reviewsData.put("total_reviews", totalReviews);
                    reviewsData.put("rating", rating);
                    tourDetails.put("reviews", reviewsData);

                    System.out.println("Datos de reviews guardados: " + reviewsData);
                }
            }

            System.out.println("prubeaaaaaaaaaaaaaa" + tourOptional);
            tourDetails.put("id", tour.getId());
            tourDetails.put("title", tour.getTitle());
            tourDetails.put("country", tour.getCountry());
            tourDetails.put("location", tour.getLocation());
            tourDetails.put("image", tour.getImage());
            tourDetails.put("description", tour.getDescription());
            tourDetails.put("long_description", tour.getLongDesciption());
            // Extraer detalles si existen
            if (tourOptional.isPresent()) {
                TourDetails details = tourOptional.get();
                Map<String, Object> detailsMap = new HashMap<>();
                Map<String, Object> pricedetails = new HashMap<>();
                pricedetails.put("amount", details.getPriceAmount());
                pricedetails.put("currency", details.getPriceCurrency());
                detailsMap.put("price", pricedetails);
                detailsMap.put("duration_days", details.getDurationDays());
                detailsMap.put("max_peple", details.getMaxPeople());
                detailsMap.put("category", details.getCategory());

                // Agrega más campos según tu entidad TourDetails
                tourDetails.put("Details", detailsMap);
            } else {
                tourDetails.put("Details", higlightsP);
            }
            tourDetails.put("highlights", higlightsP);

            return tourDetails;
        }).collect(Collectors.toList());
        Map<String, Object> json = new HashMap<>();
        json.put("tours", tourList);
        return json;
    }

    // agregar tour
    public Map<String, Object> agregarTour(TourDto tourDto) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Crear un nuevo objeto Tour
            Tours tour = new Tours();
            tour.setTitle(tourDto.getTitle());
            tour.setCountry(tourDto.getCountry());
            tour.setLocation(tourDto.getLocation());
            tour.setImage(tourDto.getImage());
            tour.setDescription(tourDto.getDescription());
            tour.setLongDesciption(tourDto.getLongDescription());

            // Guardar el tour en la base de datos
            Tours nuevoTour = toursRepository.save(tour);

            // Guardar TourDetails si se proporciona
            if (tourDto.getDetails() != null) {
                DetailsDto detailsDto = tourDto.getDetails();
                TourDetails tourDetails = new TourDetails();
                tourDetails.setTour(nuevoTour);
                tourDetails.setPriceAmount(detailsDto.getPriceAmount());
                tourDetails.setPriceCurrency(detailsDto.getPriceCurrency());
                tourDetails.setDurationDays(detailsDto.getDurationDays());
                tourDetails.setMaxPeople(detailsDto.getMaxPeople());
                tourDetails.setCategory(detailsDto.getCategory());

                // Guardar en la base de datos
                tourDetailsRepository.save(tourDetails);
            }

            // Guardar Popularidad si se proporciona
            if (tourDto.isPopular()) {
                Popularity popularity = new Popularity();
                popularity.setTour(nuevoTour);
                popularity.setPopular(true);
                popularity.setRank(tourDto.getRank());

                // Guardar en la base de datos
                popularityRepository.save(popularity);
            }

            // Guardar Reviews si se proporciona
            if (tourDto.getTotalReviews() > 0 && tourDto.getRating() > 0) {
                Reviews reviews = new Reviews();
                reviews.setTour(nuevoTour);
                reviews.setTotal(tourDto.getTotalReviews());
                reviews.setRaiting(tourDto.getRating());

                reviewsRepository.save(reviews);
            }

            // Guardar Highlights si se proporciona
            if (tourDto.getHighlights() != null) {
                for (String highlight : tourDto.getHighlights()) {
                    Highlights newHighlight = new Highlights();
                    newHighlight.setTour(nuevoTour);
                    newHighlight.setHighlight(highlight);
                    highlightsRepository.save(newHighlight);
                }
            }

            response.put("message", "Tour agregado exitosamente");
            response.put("tourId", nuevoTour.getId());

        } catch (Exception e) {
            response.put("error", "Error al agregar el tour: " + e.getMessage());
        }

        return response;
    }
    // eliminar tour
    public ResponseEntity<?> eliminarTorus( Long id){
        try {
            Tours tours = toursRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ha habiado un error con el id"));

            toursRepository.deleteById(tours.getId());
            return ResponseEntity.ok().body("se elimino el tour");
        } catch (Exception e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
      


    }
    //obtener por id
    public ResponseEntity<?> obtenerTourId(Long id){
        try {
            Tours tours = toursRepository.findById(id).orElseThrow(()-> new ExpressionException("Hay un error"));
            return ResponseEntity.ok().body(tours);
        } catch (ExpressionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
