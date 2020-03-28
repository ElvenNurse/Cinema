package mate.academy.cinema.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class OrderResponseDto {
    private List<TicketResponseDto> tickets;
    private String orderDate;
    private String userEmail;
}
