package sistema_eventos_1.backend.modules.eventos.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class EventoDTO {

    private Long id;

    private Long id_cliente;

    private  Long id_salon;

    private String nombre_salon;

    private LocalDate fecha_evento;

    private Integer cantidad_personas;

    private Integer cantidad_horas;

    private String estado;

    private Double total_pago;

    private String nombreCliente;

    private String nombreSalon;

}
