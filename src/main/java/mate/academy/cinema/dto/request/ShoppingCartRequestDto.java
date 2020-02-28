package mate.academy.cinema.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class ShoppingCartRequestDto {
    @NotNull(message = "Movie Session ID can't be null")
    @Positive(message = "Movie Session ID must be a positive number")
    private Long movieSessionId;
}
