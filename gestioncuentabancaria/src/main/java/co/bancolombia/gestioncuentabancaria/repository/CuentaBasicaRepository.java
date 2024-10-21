package co.bancolombia.gestioncuentabancaria.repository;

import co.bancolombia.gestioncuentabancaria.model.CuentaBasica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaBasicaRepository extends JpaRepository <CuentaBasica, Integer> {
}
