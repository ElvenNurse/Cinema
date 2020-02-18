package mate.academy.cinema.dto.response;

import mate.academy.cinema.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketResponseMapper {
    public TicketResponseDto getResponseDto(Ticket ticket) {
        TicketResponseDto responseDto = new TicketResponseDto();
        responseDto.setUserEmail(ticket.getUser().getEmail());
        responseDto.setMovieSessionId(ticket.getMovieSession().getId());
        responseDto.setMovieTitle(ticket.getMovieSession().getMovie().getTitle());
        responseDto.setCinemaHallId(ticket.getMovieSession().getCinemaHall().getId());
        responseDto.setShowTime(ticket.getMovieSession().getShowTime().toString());
        return responseDto;
    }
}
