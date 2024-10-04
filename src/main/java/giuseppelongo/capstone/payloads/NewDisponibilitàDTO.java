package giuseppelongo.capstone.payloads;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record NewDisponibilitàDTO(@Future(message = "La data deve essere futura")
                                  LocalDate data,

                                  @NotEmpty(message = "La fascia oraria è obbligatoria")
                                  String fasciaOraria,

                                  @Positive(message = "Il numero di posti disponibili deve essere positivo")
                                  int postiDisponibili) {
}
