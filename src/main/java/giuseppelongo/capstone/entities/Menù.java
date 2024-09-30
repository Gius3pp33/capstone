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


    @ManyToOne
    @JoinColumn(name = "amministratore_id")
    private Amministratore amministratore;
}
