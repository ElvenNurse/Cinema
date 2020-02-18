package mate.academy.cinema.dto.response;

import mate.academy.cinema.model.MovieSession;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionResponseMapper {
    public MovieSessionResponseDto getResponseDto(MovieSession movieSession) {
        MovieSessionResponseDto responseDto = new MovieSessionResponseDto();
        responseDto.setMovieTitle(movieSession.getMovie().getTitle());
        responseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        responseDto.setShowTime(movieSession.getShowTime().toString());
        return responseDto;
    }
}
