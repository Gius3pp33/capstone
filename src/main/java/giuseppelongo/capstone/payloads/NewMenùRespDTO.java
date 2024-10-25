package giuseppelongo.capstone.payloads;

public record NewMenùRespDTO(
        String id,
        String nomePiatto,
        String descrizione,
        Double prezzo,
        String categoria
) {
}
