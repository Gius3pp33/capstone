package giuseppelongo.capstone.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUtenteDTO(@NotEmpty(message = "Il nome è obbligatorio")
                           String nome,

                           @NotEmpty(message = "Il cognome è obbligatorio")
                           String cognome,

                           @Email(message = "Deve essere un'email valida")
                           String email,

                           @NotEmpty(message = "La password è obbligatoria")
                           @Size(min = 4, message = "La password deve contenere almeno 4 caratteri")
                           String password,

                           @NotEmpty(message = "Il ruolo è obbligatorio")
                           String ruolo // Può essere "ADMIN" o "CLIENTE"
) {
}
