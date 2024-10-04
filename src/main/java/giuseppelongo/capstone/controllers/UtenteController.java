package giuseppelongo.capstone.controllers;

import giuseppelongo.capstone.entities.Utente;
import giuseppelongo.capstone.payloads.NewUtenteDTO;
import giuseppelongo.capstone.payloads.NewUtenteRespDTO;
import giuseppelongo.capstone.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteRespDTO creaUtente(@RequestBody NewUtenteDTO dto) {
        Utente utente = utenteService.save(dto);
        return new NewUtenteRespDTO(utente.getId());
    }


    @GetMapping("/{id}")
    public ResponseEntity<NewUtenteRespDTO> trovaUtentePerId(@PathVariable UUID id) {
        return utenteService.findById(id)
                .map(utente -> new ResponseEntity<>(new NewUtenteRespDTO(utente.getId()), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping
    public List<NewUtenteRespDTO> trovaTuttiGliUtenti() {
        return utenteService.findAll().stream()
                .map(utente -> new NewUtenteRespDTO(utente.getId()))
                .collect(Collectors.toList());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaUtente(@PathVariable UUID id) {
        utenteService.deleteById(id);
    }
}
