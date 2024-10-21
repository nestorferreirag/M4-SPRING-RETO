package co.bancolombia.gestioncuentabancaria.model;

import co.bancolombia.gestioncuentabancaria.model.DTO.TransaccionDTO;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("CuentaBasica")
public class CuentaBasica extends Cuenta{

    public CuentaBasica(){
        super();
    }
    public CuentaBasica(Integer cuentaId, String clienteId, String numeroCuenta, BigDecimal saldo) {
        super(cuentaId, clienteId, numeroCuenta, saldo);
    }


    @Override
    public void DepositoDesdeCajero(TransaccionDTO transaccionDTO){
        //validar el tipo de cuenta en el ingreso para saber a cual apuntar

        if(transaccionDTO.getMonto().compareTo(BigDecimal.valueOf(2)) < 0) {
            throw new IllegalArgumentException("Depósito debe ser igual o superior a 2");
        }
        setSaldo(getSaldo().add(transaccionDTO.getMonto()).subtract(BigDecimal.valueOf(2)));
    }
    @Override
    public void RetiroDesdeCajero(TransaccionDTO transaccionDTO){
        if(transaccionDTO.getMonto().add(BigDecimal.valueOf(1)).compareTo(getSaldo()) > 0 ){
            throw new IllegalArgumentException("Fondos insuficientes para compra");
        }
        setSaldo(getSaldo().subtract(transaccionDTO.getMonto()).subtract(new BigDecimal("1")));
    }

}
