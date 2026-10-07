package sistema_eventos_1.backend.modules.salones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.aspectj.bridge.IMessage;

@Getter @Setter
public class SalonDTO {

    private Long id;

    @NotBlank(message = "El nombre de salon es obligatorio")
    private String nombre_salon;

    @NotNull(message = "La capacidad es obligat")
    private Integer capacidad;

    @NotNull(message = "El precio es obligatorio")
    private Double precio_renta;

    @NotBlank(message = "La ubicacion es obligatoria")
    private String ubicacion;


}
