package giuseppelongo.capstone.controllers;

import giuseppelongo.capstone.payloads.NewPrenotazioneDTO;
import giuseppelongo.capstone.payloads.NewPrenotazioneRespDTO;
import giuseppelongo.capstone.services.PrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewPrenotazioneRespDTO creaPrenotazione(@RequestBody @Valid NewPrenotazioneDTO prenotazioneDTO) {
        return prenotazioneService.creaPrenotazione(prenotazioneDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewPrenotazioneRespDTO> trovaPrenotazionePerId(@PathVariable UUID id) {
        NewPrenotazioneRespDTO prenotazione = prenotazioneService.findById(id)
                .map(p -> new NewPrenotazioneRespDTO(
                        p.getId().toString(),
                        p.getDisponibilità().getData(),
                        p.getNumeroPersone(),
                        p.getDisponibilità().getId().toString(),
                        p.getUtente().getId().toString(),
                        p.getNome(),
                        p.getRecapito()
                ))
                .orElseThrow(() -> new IllegalArgumentException("Prenotazione non trovata"));
        return ResponseEntity.ok(prenotazione);
    }

    @GetMapping
    public ResponseEntity<List<NewPrenotazioneRespDTO>> trovaTutteLePrenotazioni() {
        List<NewPrenotazioneRespDTO> prenotazioni = prenotazioneService.findAll();
        return ResponseEntity.ok(prenotazioni);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaPrenotazione(@PathVariable UUID id) {
        prenotazioneService.deleteById(id);
    }
}
