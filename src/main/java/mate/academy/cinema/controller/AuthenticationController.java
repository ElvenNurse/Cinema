package mate.academy.cinema.controller;

import mate.academy.cinema.dto.request.UserRequestDto;
import mate.academy.cinema.exception.AuthenticationException;
import mate.academy.cinema.service.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private static final Logger LOGGER = LogManager.getLogger(AuthenticationController.class);

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequestDto requestDto) {
        try {
            authenticationService.login(requestDto.getEmail(), requestDto.getPassword());
        } catch (AuthenticationException e) {
            LOGGER.error(e);
            return e.getMessage();
        }
        return "Logged in successfully";
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRequestDto requestDto) {
        authenticationService.register(requestDto.getEmail(), requestDto.getPassword());
    }
}
