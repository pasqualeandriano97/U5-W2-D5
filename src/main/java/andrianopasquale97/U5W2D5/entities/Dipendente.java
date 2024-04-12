package andrianopasquale97.U5W2D5.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String profileImage;
    @JsonIgnore
    @OneToMany(mappedBy = "dipendente")
    List<Dispositivo> dispositivi;

    public Dipendente(String username, String nome, String cognome, String email, String profileImage) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.profileImage = profileImage;
    }

}
