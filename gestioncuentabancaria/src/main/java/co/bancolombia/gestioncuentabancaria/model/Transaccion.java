package co.bancolombia.gestioncuentabancaria.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transaccionId;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuentaId1;
    private String tipoTransaccion;
    private BigDecimal monto;
    private String descripcion;
    private LocalDateTime fecha;

    public Transaccion(Integer transaccionId, Cuenta cuentaId1, String tipoTransaccion, BigDecimal monto, String descripcion, LocalDateTime fecha) {
        this.transaccionId = transaccionId;
        this.cuentaId1 = cuentaId1;
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Transaccion(){}


    public Integer getTransaccionId() {
        return transaccionId;
    }

    public void setTransaccionId(Integer transaccionId) {
        this.transaccionId = transaccionId;
    }

    public Cuenta getCuentaId1() {
        return cuentaId1;
    }

    public void setCuentaId1(Cuenta cuentaId1) {
        this.cuentaId1 = cuentaId1;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "transaccionId=" + transaccionId +
                ", cuentaId1=" + cuentaId1 +
                ", tipoTransaccion='" + tipoTransaccion + '\'' +
                ", monto=" + monto +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
