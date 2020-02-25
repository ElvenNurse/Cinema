package mate.academy.cinema.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import mate.academy.cinema.util.EmailValidation;
import mate.academy.cinema.util.PasswordMatchValidation;

@Data
@PasswordMatchValidation
public class UserRequestDto {
    @NotNull
    @EmailValidation
    private String email;
    @NotNull
    @Size(min = 4)
    private String password;
    @NotNull
    @Size(min = 4)
    private String repeatPassword;
}
