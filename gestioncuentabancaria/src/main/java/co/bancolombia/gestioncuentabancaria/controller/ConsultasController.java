package co.bancolombia.gestioncuentabancaria.controller;

import co.bancolombia.gestioncuentabancaria.model.DTO.ConsultaDTO;
import co.bancolombia.gestioncuentabancaria.service.CuentaService;
import co.bancolombia.gestioncuentabancaria.service.TransaccionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/consultas")
public class ConsultasController {
    private final CuentaService cuentaService;
    private final TransaccionService transaccionService;


    public ConsultasController(CuentaService cuentaService, TransaccionService transaccionService) {
        this.cuentaService = cuentaService;
        this.transaccionService = transaccionService;
    }

    @GetMapping("/saldo")
    public String obtenerSaldo(@Valid @RequestBody ConsultaDTO consulta){
        return cuentaService.obtenerSaldo(consulta);
    }

    @GetMapping("/transacciones")
    public String ConsultarTransacciones(@Valid @RequestBody ConsultaDTO consultaDTO){
        return cuentaService.ObtenerTransacciones(consultaDTO);
    }







}
