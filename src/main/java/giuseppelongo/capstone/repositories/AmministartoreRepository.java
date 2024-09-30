package giuseppelongo.capstone.repositories;

import giuseppelongo.capstone.entities.Amministratore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AmministartoreRepository extends JpaRepository<Amministratore, UUID> {
}
