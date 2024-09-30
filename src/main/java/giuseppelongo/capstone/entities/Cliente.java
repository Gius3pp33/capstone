package giuseppelongo.capstone.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clienti")
public class Cliente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String nome;

    private String cognome;

    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Prenotazione> prenotazioni;
}
