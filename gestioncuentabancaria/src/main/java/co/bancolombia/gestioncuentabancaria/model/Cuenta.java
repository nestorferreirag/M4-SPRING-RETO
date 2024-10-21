package co.bancolombia.gestioncuentabancaria.model;

import co.bancolombia.gestioncuentabancaria.model.DTO.TransaccionDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "cuenta")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cuenta", discriminatorType = DiscriminatorType.STRING)
public abstract class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cuentaId;
    private String clienteId;
    private String numeroCuenta;
    private BigDecimal saldo;

    @OneToMany(mappedBy = "cuentaId1")
    private List<Transaccion> transaccion;


    public Cuenta(Integer cuentaId, String clienteId, String numeroCuenta, BigDecimal saldo) {
        this.cuentaId = cuentaId;
        this.clienteId = clienteId;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }
    @Transient
    private String respuesta = "";

    public Cuenta(){}

    public Integer getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Integer cuentaId) {
        this.cuentaId = cuentaId;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public  List<Transaccion> getTransaccion() {
        return transaccion;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "cuentaId=" + cuentaId +
                '}';
    }

    public void DepositoDesdeSucursal(TransaccionDTO transaccionDTO){

        saldo = saldo.add(transaccionDTO.getMonto());
    }
    public void DepositoDesdeCuenta(TransaccionDTO transaccionDTO){
        if(transaccionDTO.getMonto().compareTo(BigDecimal.valueOf(1.5)) < 0) {
                throw new IllegalArgumentException("Depósito debe ser igual o superior a 1.5");
        }
        saldo = saldo.add(transaccionDTO.getMonto()).subtract(new BigDecimal("1.5"));
    }

    public void CompraFisica(TransaccionDTO transaccionDTO){
        if(transaccionDTO.getMonto().compareTo(saldo) > 0 ){
            throw new IllegalArgumentException("Fondos insuficientes para compra");
        }
        saldo = saldo.subtract(transaccionDTO.getMonto());
    }

    public void CompraWeb(TransaccionDTO transaccionDTO){
        BigDecimal vlCompara;
        vlCompara = transaccionDTO.getMonto().add(BigDecimal.valueOf(5));
        if(vlCompara.compareTo(saldo) > 0 ){
            throw new IllegalArgumentException("Fondos insuficientes para compra web");
        }
        saldo = saldo.subtract(vlCompara);
    }
    public void DepositoDesdeCajero(TransaccionDTO transaccionDTO){
    }

    public void RetiroDesdeCajero(TransaccionDTO transaccionDTO){

    }
    public String ConsultaHistorico(){
        transaccion.sort(Comparator.comparing(Transaccion::getFecha));

        Integer totalReg = transaccion.size();
        Integer vlConsulta = Math.max(totalReg-5,0);

        for (Transaccion transaccion : transaccion.subList(vlConsulta, totalReg)) {
            respuesta += transaccion.toString() + "\n"; // Append each transaction to the response
        }
        return respuesta;
    }
}
