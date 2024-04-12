package andrianopasquale97.U5W2D5.entities;


import andrianopasquale97.U5W2D5.enums.tipologie;
import andrianopasquale97.U5W2D5.enums.stato;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Dispositivi")
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private tipologie tipologia;
private stato stato;
@ManyToOne
@JoinColumn(name = "dipendente_id")
private Dipendente dipendente;

    public Dispositivo(tipologie tipologia, stato stato) {
        this.tipologia = tipologia;
        this.stato = stato;}
}
