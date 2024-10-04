package giuseppelongo.capstone.controllers;

import giuseppelongo.capstone.entities.Utente;
import giuseppelongo.capstone.exceptions.BadRequestException;
import giuseppelongo.capstone.payloads.NewUtenteDTO;
import giuseppelongo.capstone.payloads.NewUtenteRespDTO;
import giuseppelongo.capstone.payloads.UtenteLoginDTO;
import giuseppelongo.capstone.payloads.UtenteLoginRespDTO;
import giuseppelongo.capstone.services.AuthService;
import giuseppelongo.capstone.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public UtenteLoginRespDTO login(@RequestBody UtenteLoginDTO payload) {
        String token = this.authService.checkCredentialsAndGenerateToken(payload);
        return new UtenteLoginRespDTO(token);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteRespDTO register(@RequestBody @Validated NewUtenteDTO body, BindingResult validationResult) {
        // Check for validation errors
        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        }


        Utente savedUtente = utenteService.save(body);
        return new NewUtenteRespDTO(savedUtente.getId());
    }
}
