package co.bancolombia.gestioncuentabancaria.repository;

import co.bancolombia.gestioncuentabancaria.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {

}
