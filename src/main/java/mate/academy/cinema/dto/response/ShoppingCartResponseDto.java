package mate.academy.cinema.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class ShoppingCartResponseDto {
    private List<TicketResponseDto> tickets;
    private String userEmail;
}
