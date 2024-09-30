package giuseppelongo.capstone.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "disponibilità")
public class Disponibilità {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private LocalDate data;

    private String fasciaOraria;

    private int postiDisponibili;

    @OneToMany(mappedBy = "disponibilità")
    private List<Prenotazione> prenotazioni;
}
