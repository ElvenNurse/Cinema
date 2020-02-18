package mate.academy.cinema.dto.response;

import lombok.Data;

@Data
public class MovieSessionResponseDto {
    private String movieTitle;
    private Long cinemaHallId;
    private String showTime;
}
