package giuseppelongo.capstone.payloads;

import java.time.LocalDate;

public record NewDisponibilitàRespDTO(String id,
                                      LocalDate data,
                                      String fasciaOraria,
                                      int postiDisponibili) {
}
