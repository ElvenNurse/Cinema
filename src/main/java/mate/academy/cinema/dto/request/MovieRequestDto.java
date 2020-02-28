package mate.academy.cinema.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MovieRequestDto {
    @NotNull(message = "Title can't be null")
    private String title;
    private String description;
}
