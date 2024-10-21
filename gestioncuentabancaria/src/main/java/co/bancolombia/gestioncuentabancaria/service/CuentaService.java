package co.bancolombia.gestioncuentabancaria.service;

import co.bancolombia.gestioncuentabancaria.model.CuentaBasica;
import co.bancolombia.gestioncuentabancaria.model.CuentaPremium;
import co.bancolombia.gestioncuentabancaria.model.DTO.ConsultaDTO;
import co.bancolombia.gestioncuentabancaria.model.DTO.TransaccionDTO;
import co.bancolombia.gestioncuentabancaria.repository.CuentaBasicaRepository;
import co.bancolombia.gestioncuentabancaria.repository.CuentaPremiumRepository;
import co.bancolombia.gestioncuentabancaria.repository.TransaccionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Service

public class CuentaService {
    private final CuentaBasicaRepository cuentaBasicaRepository;
    private final CuentaPremiumRepository cuentaPremiumRepository;
    private final TransaccionRepository transaccionRepository;

    public CuentaService(CuentaBasicaRepository cuentaBasicaRepository, CuentaPremiumRepository cuentaPremiumRepository, TransaccionRepository transaccionRepository) {
        this.cuentaBasicaRepository = cuentaBasicaRepository;
        this.cuentaPremiumRepository = cuentaPremiumRepository;
        this.transaccionRepository = transaccionRepository;
    }
    public String obtenerSaldo(ConsultaDTO consultaDTO){
        //Buscar en CuentaBasica
        CuentaBasica cuentaBasica = cuentaBasicaRepository.findById(consultaDTO.getCuentaId()).orElse(null);
        if (cuentaBasica != null) {
            return "El saldo para la cuenta " + consultaDTO.getCuentaId() + " es de: " + cuentaBasica.getSaldo();
        }else {

            //Buscar en CuentaPremium
            CuentaPremium cuentaPremium = cuentaPremiumRepository.findById(consultaDTO.getCuentaId()).orElse(null);
            if (cuentaPremium != null) {
                return "El saldo para la cuenta " + consultaDTO.getCuentaId() + " es de: " + cuentaPremium.getSaldo();
            } else {
                throw new NoSuchElementException("La cuenta " + consultaDTO.getCuentaId() + " no fue encontrada");
            }
        }

    }
    public BigDecimal obtenerSaldo1(TransaccionDTO transaccionDTO) {
        //Buscar en CuentaBasica
        CuentaBasica cuentaBasica = cuentaBasicaRepository.findById(transaccionDTO.getCuenta()).orElse(null);
        if (cuentaBasica != null) {
            return cuentaBasica.getSaldo();
        }else {
            //Buscar en CuentaPremium
            CuentaPremium cuentaPremium = cuentaPremiumRepository.findById(transaccionDTO.getCuenta()).orElse(null);
            if (cuentaPremium != null) {
                return cuentaPremium.getSaldo();
            } else {
                throw new NoSuchElementException("La cuenta " + transaccionDTO.getCuenta() + " no fue encontrada");
            }
        }
    }

    public String ObtenerTransacciones(ConsultaDTO consultaDTO){
        //Buscar en CuentaBasica
        CuentaBasica cuentaBasica = cuentaBasicaRepository.findById(consultaDTO.getCuentaId()).orElse(null);
        if (cuentaBasica != null) {
            return cuentaBasica.ConsultaHistorico();
        }

        //Buscar en CuentaPremium
        CuentaPremium cuentaPremium = cuentaPremiumRepository.findById(consultaDTO.getCuentaId()).orElse(null);
        if (cuentaPremium != null) {
            return cuentaPremium.ConsultaHistorico();
        } else {
            throw new NoSuchElementException("La cuenta " + consultaDTO.getCuentaId() + " no fue encontrada");
        }
    }

    public void DepositoDesdeSucursal(TransaccionDTO transaccionDTO){
        //Buscar en CuentaBasica
        CuentaBasica cuentaBasica = cuentaBasicaRepository.findById(transaccionDTO.getCuenta()).orElse(null);
        if (cuentaBasica != null) {
            cuentaBasica.DepositoDesdeSucursal(transaccionDTO);
            cuentaBasicaRepository.save(cuentaBasica);
        }else {

            //Buscar en CuentaPremium
            CuentaPremium cuentaPremium = cuentaPremiumRepository.findById(transaccionDTO.getCuenta()).orElse(null);
            if (cuentaPremium != null) {
                cuentaPremium.DepositoDesdeSucursal(transaccionDTO);
                cuentaPremiumRepository.save(cuentaPremium);
            } else {
                throw new NoSuchElementException("La cuenta " + transaccionDTO.getCuenta() + " no fue encontrada");
            }
        }
    }
    public void DepositoDesdeCuenta(TransaccionDTO transaccionDTO){
        //Buscar en CuentaBasica
        CuentaBasica cuentaBasica = cuentaBasicaRepository.findById(transaccionDTO.getCuenta()).orElse(null);
        if (cuentaBasica != null) {
            cuentaBasica.DepositoDesdeCuenta(transaccionDTO);
            cuentaBasicaRepository.save(cuentaBasica);
        }else {

            //Buscar en CuentaPremium
            CuentaPremium cuentaPremium = cuentaPremiumRepository.findById(transaccionDTO.getCuenta()).orElse(null);
            if (cuentaPremium != null) {
                cuentaPremium.DepositoDesdeCuenta(transaccionDTO);
                cuentaPremiumRepository.save(cuentaPremium);
            } else {
                throw new NoSuchElementException("La cuenta " + transaccionDTO.getCuenta() + " no fue encontrada");
            }
        }
    }
    public void CompraFisica(TransaccionDTO transaccionDTO){
        //Buscar en CuentaBasica
        CuentaBasica cuentaBasica = cuentaBasicaRepository.findById(transaccionDTO.getCuenta()).orElse(null);
        if (cuentaBasica != null) {
            cuentaBasica.CompraFisica(transaccionDTO);
            cuentaBasicaRepository.save(cuentaBasica);
        }else {
            //Buscar en CuentaPremium
            CuentaPremium cuentaPremium = cuentaPremiumRepository.findById(transaccionDTO.getCuenta()).orElse(null);
            if (cuentaPremium != null) {
                cuentaPremium.CompraFisica(transaccionDTO);
                cuentaPremiumRepository.save(cuentaPremium);
            } else {
                throw new NoSuchElementException("La cuenta " + transaccionDTO.getCuenta() + " no fue encontrada");
            }
        }
    }
    public void CompraWeb(TransaccionDTO transaccionDTO){
        //Buscar en CuentaBasica
        CuentaBasica cuentaBasica = cuentaBasicaRepository.findById(transaccionDTO.getCuenta()).orElse(null);
        if (cuentaBasica != null) {
            cuentaBasica.CompraWeb(transaccionDTO);
            cuentaBasicaRepository.save(cuentaBasica);
        }else {
            //Buscar en CuentaPremium
            CuentaPremium cuentaPremium = cuentaPremiumRepository.findById(transaccionDTO.getCuenta()).orElse(null);
            if (cuentaPremium != null) {
                cuentaPremium.CompraWeb(transaccionDTO);
                cuentaPremiumRepository.save(cuentaPremium);
            } else {
                throw new NoSuchElementException("La cuenta " + transaccionDTO.getCuenta() + " no fue encontrada");
            }
        }
    }

    public void DepositoDesdeCajero(TransaccionDTO transaccionDTO) {
        //Buscar en CuentaBasica
        CuentaBasica cuentaBasica = cuentaBasicaRepository.findById(transaccionDTO.getCuenta()).orElse(null);
        if (cuentaBasica != null) {
            cuentaBasica.DepositoDesdeCajero(transaccionDTO);
            cuentaBasicaRepository.save(cuentaBasica);
        }else {
            //Buscar en CuentaPremium
            CuentaPremium cuentaPremium = cuentaPremiumRepository.findById(transaccionDTO.getCuenta()).orElse(null);
            if (cuentaPremium != null) {
                cuentaPremium.DepositoDesdeCajero(transaccionDTO);
                cuentaPremiumRepository.save(cuentaPremium);
            } else {
                throw new NoSuchElementException("La cuenta " + transaccionDTO.getCuenta() + " no fue encontrada");
            }
        }
    }
    public void RetiroCajero(TransaccionDTO transaccionDTO){
        //Buscar en CuentaBasica
        CuentaBasica cuentaBasica = cuentaBasicaRepository.findById(transaccionDTO.getCuenta()).orElse(null);
        if (cuentaBasica != null) {
            cuentaBasica.RetiroDesdeCajero(transaccionDTO);
            cuentaBasicaRepository.save(cuentaBasica);
        }else {
            //Buscar en CuentaPremium
            CuentaPremium cuentaPremium = cuentaPremiumRepository.findById(transaccionDTO.getCuenta()).orElse(null);
            if (cuentaPremium != null) {
                cuentaPremium.RetiroDesdeCajero(transaccionDTO);
                cuentaPremiumRepository.save(cuentaPremium);
            } else {
                throw new NoSuchElementException("La cuenta " + transaccionDTO.getCuenta() + " no fue encontrada");
            }
        }
    }
}
