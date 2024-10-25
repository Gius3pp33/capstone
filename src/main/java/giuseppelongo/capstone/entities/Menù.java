package giuseppelongo.capstone.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "menù")
public class Menù {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String nomePiatto;

    private String descrizione;

    private Double prezzo;

    private String categoria;

    @ManyToOne
    @JoinColumn(name = "amministratore_id")
    private Utente utente;
}
