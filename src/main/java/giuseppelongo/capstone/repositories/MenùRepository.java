package giuseppelongo.capstone.repositories;

import giuseppelongo.capstone.entities.Menù;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenùRepository extends JpaRepository<Menù, UUID> {
}
