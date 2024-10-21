package co.bancolombia.gestioncuentabancaria.controller;

import co.bancolombia.gestioncuentabancaria.model.DTO.TransaccionDTO;
import co.bancolombia.gestioncuentabancaria.service.CuentaService;
import co.bancolombia.gestioncuentabancaria.service.TransaccionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/operaciones")
public class OperacionesController {
    private final CuentaService cuentaService;
    private final TransaccionService transaccionService;
    private BigDecimal saldo;

    public OperacionesController(CuentaService cuentaService, TransaccionService transaccionService) {
        this.cuentaService = cuentaService;
        this.transaccionService = transaccionService;
    }

    @PostMapping("/depositoDesdeSucursal")
    public String DepositoDesdeSucursal(@Valid @RequestBody TransaccionDTO transaccionDTO){
        cuentaService.DepositoDesdeSucursal(transaccionDTO);
        transaccionService.CreaTransaccion(transaccionDTO,"depósito");
        saldo = cuentaService.obtenerSaldo1(transaccionDTO);
        return "Depósito realizado con exito, el nuevo saldo es de: " + saldo ;
    }

    @PostMapping("/depositoDesdeCuenta")
    public String DepositoDesdeCuenta(@Valid @RequestBody TransaccionDTO transaccionDTO){
        cuentaService.DepositoDesdeCuenta(transaccionDTO);
        transaccionService.CreaTransaccion(transaccionDTO,"depósito");
        saldo = cuentaService.obtenerSaldo1(transaccionDTO);
        return "Depósito realizado con exito, el nuevo saldo es de: " + saldo ;
    }
    @PostMapping("/compraFisica")
    public String CompraFisica(@Valid @RequestBody TransaccionDTO transaccionDTO){
        cuentaService.CompraFisica(transaccionDTO);
        transaccionService.CreaTransaccion(transaccionDTO,"compra");
        saldo = cuentaService.obtenerSaldo1(transaccionDTO);
        return "Compra realizada con exito, el nuevo saldo es de: " + saldo ;
    }
    @PostMapping("/compraWeb")
    public String CompraWeb(@Valid @RequestBody TransaccionDTO transaccionDTO){
        cuentaService.CompraWeb(transaccionDTO);
        transaccionService.CreaTransaccion(transaccionDTO,"compra");
        saldo = cuentaService.obtenerSaldo1(transaccionDTO);
        return "Compra realizada con exito, el nuevo saldo es de: " + saldo ;
    }
    @PostMapping("/depositoDesdeCajero")
    public String DepositoDesdeCajero(@Valid @RequestBody TransaccionDTO transaccionDTO){
        cuentaService.DepositoDesdeCajero(transaccionDTO);
        transaccionService.CreaTransaccion(transaccionDTO,"depósito");
        saldo = cuentaService.obtenerSaldo1(transaccionDTO);
        return "Depósito realizado con exito, el nuevo saldo es de: " + saldo ;
    }
    @PostMapping("/retiroCajero")
    public String RetiroCajero(@Valid @RequestBody TransaccionDTO transaccionDTO){
        cuentaService.RetiroCajero(transaccionDTO);
        transaccionService.CreaTransaccion(transaccionDTO,"retiro");
        saldo = cuentaService.obtenerSaldo1(transaccionDTO);
        return "Retiro realizado con exito, el nuevo saldo es de: "+ saldo;
    }
}
