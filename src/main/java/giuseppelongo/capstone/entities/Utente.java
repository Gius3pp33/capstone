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
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String nome;
    private String cognome;
    private String email;
    private String password;


    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    @OneToMany(mappedBy = "utente")
    private List<Prenotazione> prenotazioni;

    @OneToMany(mappedBy = "utente")
    private List<Menù> menù;
}
