package giuseppelongo.capstone.payloads;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record NewMenùDTO(
        @NotEmpty(message = "Il nome del piatto è obbligatorio")
        String nomePiatto,

        @NotEmpty(message = "La descrizione è obbligatoria")
        String descrizione,


        Double prezzo,


        UUID utenteId


) {
}