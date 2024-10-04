package giuseppelongo.capstone.controllers;

import giuseppelongo.capstone.payloads.NewDisponibilitàDTO;
import giuseppelongo.capstone.payloads.NewDisponibilitàRespDTO;
import giuseppelongo.capstone.services.DisponibilitàService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/disponibilità")
public class DisponibilitàController {

    @Autowired
    private DisponibilitàService disponibilitàService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewDisponibilitàRespDTO creaDisponibilità(@RequestBody @Valid NewDisponibilitàDTO disponibilitàDTO) {
        return disponibilitàService.creaDisponibilità(disponibilitàDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewDisponibilitàRespDTO> trovaDisponibilitàPerId(@PathVariable UUID id) {
        NewDisponibilitàRespDTO disponibilità = disponibilitàService.findById(id)
                .map(d -> new NewDisponibilitàRespDTO(
                        d.getId().toString(),
                        d.getData(),
                        d.getFasciaOraria(),
                        d.getPostiDisponibili()
                ))
                .orElseThrow(() -> new IllegalArgumentException("Disponibilità non trovata"));
        return ResponseEntity.ok(disponibilità);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaDisponibilità(@PathVariable UUID id) {
        disponibilitàService.deleteById(id);
    }
}
