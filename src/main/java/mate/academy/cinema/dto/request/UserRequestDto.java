package mate.academy.cinema.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import mate.academy.cinema.util.EmailValidation;
import mate.academy.cinema.util.PasswordMatchValidation;

@Data
@PasswordMatchValidation
public class UserRequestDto {
    @NotNull(message = "Email can't be null")
    @EmailValidation
    private String email;
    @NotNull(message = "Password can't be null")
    @Size(min = 4, message = "Password minimal length is 4 symbols")
    private String password;
    @NotNull(message = "Password can't be null")
    @Size(min = 4, message = "Password minimal length is 4 symbols")
    private String repeatPassword;
}
