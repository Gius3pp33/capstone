package giuseppelongo.capstone.repositories;

import giuseppelongo.capstone.entities.Disponibilità;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DisponibilitàRepository extends JpaRepository<Disponibilità, UUID> {
}
