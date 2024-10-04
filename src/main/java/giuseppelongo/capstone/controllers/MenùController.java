package giuseppelongo.capstone.controllers;

import giuseppelongo.capstone.entities.Menù;
import giuseppelongo.capstone.entities.Utente;
import giuseppelongo.capstone.payloads.NewMenùDTO;
import giuseppelongo.capstone.payloads.NewMenùRespDTO;
import giuseppelongo.capstone.services.MenùService;
import giuseppelongo.capstone.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menù")
public class MenùController {

    @Autowired
    private MenùService menùService;

    @Autowired
    private UtenteService utenteService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewMenùRespDTO creaMenù(@RequestBody NewMenùDTO dto) {
        UUID utenteId = dto.utenteId();
        Utente utente = utenteService.findById(utenteId)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
        Menù menù = menùService.save(dto, utente);
        return new NewMenùRespDTO(menù.getId().toString(), menù.getNomePiatto(), menù.getDescrizione(), menù.getPrezzo());
    }


    @GetMapping("/{id}")
    public ResponseEntity<NewMenùRespDTO> trovaMenùPerId(@PathVariable UUID id) {
        Optional<Menù> menùOpt = menùService.findById(id);
        if (menùOpt.isPresent()) {
            Menù menù = menùOpt.get();
            return ResponseEntity.ok(new NewMenùRespDTO(menù.getId().toString(), menù.getNomePiatto(), menù.getDescrizione(), menù.getPrezzo()));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public List<NewMenùRespDTO> trovaTuttiIMenù() {
        return menùService.findAll().stream()
                .map(menù -> new NewMenùRespDTO(menù.getId().toString(), menù.getNomePiatto(), menù.getDescrizione(), menù.getPrezzo()))
                .collect(Collectors.toList());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaMenù(@PathVariable UUID id) {
        menùService.deleteById(id);
    }
}
