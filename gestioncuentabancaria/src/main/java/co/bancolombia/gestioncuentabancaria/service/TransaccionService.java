package co.bancolombia.gestioncuentabancaria.service;

import co.bancolombia.gestioncuentabancaria.model.Cuenta;
import co.bancolombia.gestioncuentabancaria.model.CuentaBasica;
import co.bancolombia.gestioncuentabancaria.model.CuentaPremium;
import co.bancolombia.gestioncuentabancaria.model.DTO.TransaccionDTO;
import co.bancolombia.gestioncuentabancaria.model.Transaccion;
import co.bancolombia.gestioncuentabancaria.repository.CuentaBasicaRepository;
import co.bancolombia.gestioncuentabancaria.repository.CuentaPremiumRepository;
import co.bancolombia.gestioncuentabancaria.repository.TransaccionRepository;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TransaccionService {
    private final TransaccionRepository transaccionRepository;
    private final CuentaBasicaRepository cuentaBasicaRepository;
    private final CuentaPremiumRepository cuentaPremiumRepository;

    public TransaccionService(TransaccionRepository transaccionRepository, CuentaBasicaRepository cuentaBasicaRepository, CuentaPremiumRepository cuentaPremiumRepository) {
        this.transaccionRepository = transaccionRepository;
        this.cuentaBasicaRepository = cuentaBasicaRepository;
        this.cuentaPremiumRepository = cuentaPremiumRepository;
    }

    public void CreaTransaccion(TransaccionDTO transaccionDTO, String tipoTx){
        //Buscar en CuentaBasica
        CuentaBasica cuentaBasica = cuentaBasicaRepository.findById(transaccionDTO.getCuenta()).orElse(null);
        if (cuentaBasica == null) {
            CuentaPremium cuentaPremium = cuentaPremiumRepository.findById(transaccionDTO.getCuenta()).orElse(null);
            if (cuentaPremium == null) {
                throw new NoSuchElementException("La cuenta " + transaccionDTO.getCuenta() + " no fue encontrada");
            }
            GuardaTransaccion(cuentaPremium, tipoTx, transaccionDTO);
        }else {
            GuardaTransaccion(cuentaBasica, tipoTx, transaccionDTO);
        }
    }

    private void GuardaTransaccion(Object cuenta, String tipoTx, TransaccionDTO transaccionDTO) {

                Transaccion transaccion = new Transaccion();
            if (cuenta instanceof CuentaBasica) {
              transaccion.setCuentaId1((CuentaBasica) cuenta);
            } else if (cuenta instanceof CuentaPremium) {
              transaccion.setCuentaId1((CuentaPremium) cuenta);
            }
                //transaccion.setCuentaId1(cuenta);
                transaccion.setTipoTransaccion(tipoTx);
                transaccion.setMonto(transaccionDTO.getMonto());
                transaccion.setDescripcion("Transacción de tipo: " + tipoTx);
                transaccion.setFecha(LocalDateTime.now());
                transaccionRepository.save(transaccion);

    }
}
