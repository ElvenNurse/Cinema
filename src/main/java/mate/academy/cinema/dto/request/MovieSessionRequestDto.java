package mate.academy.cinema.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class MovieSessionRequestDto {
    @NotNull(message = "Movie ID can't be null")
    @Positive(message = "Movie ID must be a positive number")
    private Long movieId;
    @NotNull(message = "Cinema Hall ID can't be null")
    @Positive(message = "Cinema Hall ID must be a positive number")
    private Long cinemaHallId;
    @NotNull(message = "Show Time can't be null")
    private String showTime;
}
