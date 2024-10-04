package giuseppelongo.capstone.services;

import giuseppelongo.capstone.entities.Disponibilità;
import giuseppelongo.capstone.payloads.NewDisponibilitàDTO;
import giuseppelongo.capstone.payloads.NewDisponibilitàRespDTO;
import giuseppelongo.capstone.repositories.DisponibilitàRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DisponibilitàService {

    @Autowired
    private DisponibilitàRepository disponibilitàRepository;

    public NewDisponibilitàRespDTO creaDisponibilità(NewDisponibilitàDTO dto) {
        Disponibilità disponibilità = new Disponibilità();
        disponibilità.setData(dto.data());
        disponibilità.setFasciaOraria(dto.fasciaOraria());
        disponibilità.setPostiDisponibili(dto.postiDisponibili());

        Disponibilità disponibilitàSalvata = disponibilitàRepository.save(disponibilità);

        return new NewDisponibilitàRespDTO(
                disponibilitàSalvata.getId().toString(),
                disponibilitàSalvata.getData(),
                disponibilitàSalvata.getFasciaOraria(),
                disponibilitàSalvata.getPostiDisponibili()
        );
    }

    public Optional<Disponibilità> findById(UUID id) {
        return disponibilitàRepository.findById(id);
    }


    public Disponibilità save(Disponibilità disponibilità) {
        return disponibilitàRepository.save(disponibilità);
    }

    public List<NewDisponibilitàRespDTO> findAll() {
        return disponibilitàRepository.findAll().stream()
                .map(disponibilità -> new NewDisponibilitàRespDTO(
                        disponibilità.getId().toString(),
                        disponibilità.getData(),
                        disponibilità.getFasciaOraria(),
                        disponibilità.getPostiDisponibili()))
                .collect(Collectors.toList());
    }

    public void deleteById(UUID id) {
        disponibilitàRepository.deleteById(id);
    }
}
