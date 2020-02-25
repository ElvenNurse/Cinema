package mate.academy.cinema.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import mate.academy.cinema.dto.request.UserRequestDto;

public class CustomPasswordMatchValidator implements
        ConstraintValidator<PasswordMatchValidation, UserRequestDto> {
    @Override
    public boolean isValid(UserRequestDto requestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        return requestDto.getPassword().equals(requestDto.getRepeatPassword());
    }
}
