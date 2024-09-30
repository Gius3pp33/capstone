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
@Table(name = "amministratori")
public class Amministratore {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String email;

    private String password;

    @OneToMany(mappedBy = "amministratore")
    private List<Menù> menù;
}
