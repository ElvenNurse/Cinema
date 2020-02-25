package mate.academy.cinema.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class MovieSessionRequestDto {
    @NotNull
    @Positive
    private Long movieId;
    @NotNull
    @Positive
    private Long cinemaHallId;
    @NotNull
    private String showTime;
}
