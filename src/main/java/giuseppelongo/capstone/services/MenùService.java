package giuseppelongo.capstone.services;

import giuseppelongo.capstone.entities.Menù;
import giuseppelongo.capstone.entities.Ruolo;
import giuseppelongo.capstone.entities.Utente;
import giuseppelongo.capstone.exceptions.NotFoundException;
import giuseppelongo.capstone.exceptions.UnauthorizedException;
import giuseppelongo.capstone.payloads.NewMenùDTO;
import giuseppelongo.capstone.repositories.MenùRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenùService {

    @Autowired
    private MenùRepository menùRepository;

    public Menù save(NewMenùDTO dto, Utente utente) {

        if (!utente.getRuolo().equals(Ruolo.ADMIN) && !utente.getRuolo().equals(Ruolo.CLIENTE)) {
            throw new UnauthorizedException("L'utente non ha i permessi necessari.");
        }

        Menù menù = new Menù();
        menù.setNomePiatto(dto.nomePiatto());
        menù.setDescrizione(dto.descrizione());
        menù.setPrezzo(dto.prezzo());

        menù.setUtente(utente);

        return menùRepository.save(menù);
    }

    public Optional<Menù> findById(UUID id) {
        return menùRepository.findById(id);
    }

    public List<Menù> findAll() {
        return menùRepository.findAll();
    }

    public void deleteById(UUID id) {
        if (!menùRepository.existsById(id)) {
            throw new NotFoundException("Menù non trovato!");
        }
        menùRepository.deleteById(id);
    }
}
