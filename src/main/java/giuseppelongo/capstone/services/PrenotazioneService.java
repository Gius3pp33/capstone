package giuseppelongo.capstone.services;

import giuseppelongo.capstone.entities.Disponibilità;
import giuseppelongo.capstone.entities.Prenotazione;
import giuseppelongo.capstone.entities.Utente;
import giuseppelongo.capstone.payloads.NewPrenotazioneDTO;
import giuseppelongo.capstone.payloads.NewPrenotazioneRespDTO;
import giuseppelongo.capstone.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DisponibilitàService disponibilitàService;

    @Autowired
    private UtenteService utenteService;

    public NewPrenotazioneRespDTO creaPrenotazione(NewPrenotazioneDTO dto) {
        Disponibilità disponibilità = disponibilitàService.findById(UUID.fromString(dto.disponibilitàId()))
                .orElseThrow(() -> new IllegalArgumentException("Disponibilità non trovata"));
        Utente utente = utenteService.findById(UUID.fromString(dto.utenteId()))
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        if (disponibilità.getPostiDisponibili() < dto.numeroPersone()) {
            throw new IllegalArgumentException("Numero di posti prenotati eccede i posti disponibili");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDisponibilità(disponibilità);
        prenotazione.setUtente(utente);
        prenotazione.setNumeroPersone(dto.numeroPersone());
        prenotazione.setData(dto.data());

        disponibilità.setPostiDisponibili(disponibilità.getPostiDisponibili() - dto.numeroPersone());
        disponibilitàService.save(disponibilità);

        Prenotazione prenotazioneSalvata = prenotazioneRepository.save(prenotazione);

        return new NewPrenotazioneRespDTO(
                prenotazioneSalvata.getId().toString(),
                disponibilità.getData(),
                prenotazioneSalvata.getNumeroPersone(),
                disponibilità.getId().toString(),
                utente.getId().toString()

        );
    }

    public Optional<Prenotazione> findById(UUID id) {
        return prenotazioneRepository.findById(id);
    }

    public List<NewPrenotazioneRespDTO> findAll() {
        return prenotazioneRepository.findAll().stream()
                .map(prenotazione -> new NewPrenotazioneRespDTO(
                        prenotazione.getId().toString(),
                        prenotazione.getDisponibilità().getData(),
                        prenotazione.getNumeroPersone(),
                        prenotazione.getDisponibilità().getId().toString(),
                        prenotazione.getUtente().getId().toString()


                ))
                .collect(Collectors.toList());
    }

    public void deleteById(UUID id) {
        prenotazioneRepository.deleteById(id);
    }
}
