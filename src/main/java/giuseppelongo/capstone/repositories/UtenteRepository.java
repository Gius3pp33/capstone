package giuseppelongo.capstone.repositories;

import giuseppelongo.capstone.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UtenteRepository extends JpaRepository<Utente, UUID> {
    Optional<Utente> findByEmail(String email);
}
