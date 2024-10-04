package giuseppelongo.capstone.payloads;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record NewPrenotazioneDTO(
        @Future(message = "La data deve essere futura")
        LocalDate data,

        @Positive(message = "Il numero di persone deve essere maggiore di zero")
        int numeroPersone,

        @NotEmpty(message = "L'ID del cliente è obbligatorio")
        String utenteId,

        @NotEmpty(message = "L'ID della disponibilità è obbligatorio")
        String disponibilitàId
) {
}