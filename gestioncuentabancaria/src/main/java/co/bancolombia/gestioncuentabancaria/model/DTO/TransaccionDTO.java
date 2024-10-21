package co.bancolombia.gestioncuentabancaria.model.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class TransaccionDTO {
    @NotNull(message = "El numero de cuenta es obligatorio")
    private Integer cuenta;
    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "Solo se permiten valores positivos")
    private BigDecimal monto;


    public TransaccionDTO(){

    }
    public TransaccionDTO( Integer cuenta, BigDecimal monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }

    public Integer getCuenta() {
        return cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }

    public BigDecimal getMonto() {
        return monto;
    }
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
