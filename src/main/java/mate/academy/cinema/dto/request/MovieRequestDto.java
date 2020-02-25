package mate.academy.cinema.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class MovieRequestDto {
    @NotNull
    private String title;
    private String description;
}
