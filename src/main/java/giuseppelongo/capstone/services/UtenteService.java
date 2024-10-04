package giuseppelongo.capstone.services;

import giuseppelongo.capstone.entities.Ruolo;
import giuseppelongo.capstone.entities.Utente;
import giuseppelongo.capstone.exceptions.NotFoundException;
import giuseppelongo.capstone.payloads.NewUtenteDTO;
import giuseppelongo.capstone.repositories.UtenteRepository;
import giuseppelongo.capstone.tools.MailgunSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailgunSender mailgunSender;

    public Utente save(NewUtenteDTO dto) {
        Utente utente = new Utente();
        utente.setNome(dto.nome());
        utente.setCognome(dto.cognome());
        utente.setEmail(dto.email());
        utente.setPassword(passwordEncoder.encode(dto.password()));

        try {
            Ruolo ruolo = Ruolo.valueOf(dto.ruolo().toUpperCase());
            utente.setRuolo(ruolo);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Ruolo non valido: " + dto.ruolo());
        }

        Utente utenteSalvato = utenteRepository.save(utente);


        mailgunSender.sendRegistrationEmail(utenteSalvato);

        return utenteSalvato;
    }

    public Optional<Utente> findByEmail(String email) {
        return utenteRepository.findByEmail(email);
    }

    public Optional<Utente> findById(UUID id) {
        return utenteRepository.findById(id);
    }

    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }

    public void deleteById(UUID id) {
        if (!utenteRepository.existsById(id)) {
            throw new NotFoundException("Utente non trovato!");
        }
        utenteRepository.deleteById(id);
    }
}
