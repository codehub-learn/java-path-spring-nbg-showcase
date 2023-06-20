package gr.codelearn.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Itinerary {
    private Integer id;
    private String departureAirportCode;
    private String destinationAirportCode;
    private LocalDateTime departureDate;
    private String airline;
    private BigDecimal basicPrice;
}
