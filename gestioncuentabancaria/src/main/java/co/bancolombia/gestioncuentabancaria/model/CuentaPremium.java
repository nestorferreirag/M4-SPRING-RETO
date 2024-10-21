package co.bancolombia.gestioncuentabancaria.model;

import co.bancolombia.gestioncuentabancaria.model.DTO.TransaccionDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;
@Entity
@DiscriminatorValue("CuentaPremium")
public class CuentaPremium extends Cuenta{

    public CuentaPremium(){
        super();
    }
    public CuentaPremium(Integer cuentaId, String clienteId, String numeroCuenta,  BigDecimal saldo) {
        super(cuentaId, clienteId, numeroCuenta, saldo);
    }
    @Override
    public void DepositoDesdeCajero(TransaccionDTO transaccionDTO) {
        setSaldo(getSaldo().add(transaccionDTO.getMonto()));
    }
    @Override
    public void RetiroDesdeCajero(TransaccionDTO transaccionDTO){
        if(transaccionDTO.getMonto().compareTo(getSaldo()) > 0 ){
            throw new IllegalArgumentException("Fondos insuficientes para compra");
        }
        setSaldo(getSaldo().subtract(transaccionDTO.getMonto()));
    }
}
