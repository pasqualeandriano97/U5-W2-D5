package andrianopasquale97.U5W2D5.repositories;

import andrianopasquale97.U5W2D5.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendentiDAO extends JpaRepository<Dipendente, Integer> {
    boolean existsByEmail(String email);
    Optional<Dipendente> findByEmail(String email);
}
