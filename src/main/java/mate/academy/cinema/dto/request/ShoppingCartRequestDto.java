package mate.academy.cinema.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class ShoppingCartRequestDto {
    @NotNull
    @Positive
    private Long movieSessionId;
}
