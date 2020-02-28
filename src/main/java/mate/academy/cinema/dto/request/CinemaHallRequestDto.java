package mate.academy.cinema.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class CinemaHallRequestDto {
    @NotNull(message = "Capacity can't be null")
    @Positive(message = "Capacity must be a positive number")
    private Integer capacity;
    private String description;
}
