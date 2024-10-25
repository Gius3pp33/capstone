package giuseppelongo.capstone.payloads;

import java.time.LocalDate;

public record NewPrenotazioneRespDTO(String id,
                                     LocalDate data,
                                     int numeroPersone,
                                     String nome,
                                     String recapito,
                                     String clienteId,
                                     String disponibilitàId) {
}
