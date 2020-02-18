package mate.academy.cinema.dto.response;

import lombok.Data;

@Data
public class TicketResponseDto {
    private Long movieSessionId;
    private String movieTitle;
    private Long cinemaHallId;
    private String showTime;
    private String userEmail;
}
