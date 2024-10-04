package giuseppelongo.capstone.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UtenteLoginDTO(@NotEmpty(message = "L'email è obbligatoria")
                             @Email(message = "L'email deve essere valida")
                             String email,

                             @NotEmpty(message = "La password è obbligatoria")
                             @Size(min = 4, message = "La password deve avere almeno 4 caratteri")
                             String password) {
}
