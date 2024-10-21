package co.bancolombia.gestioncuentabancaria.model.DTO;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public class ConsultaDTO {

    @NotNull(message = "El numero de cuenta es obligatorio")
    private Integer cuentaId;

    public ConsultaDTO(Integer cuentaId) {
        this.cuentaId = cuentaId;
    }
    public ConsultaDTO() {}
    public Integer getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Integer cuentaId) {
        this.cuentaId = cuentaId;
    }
}
