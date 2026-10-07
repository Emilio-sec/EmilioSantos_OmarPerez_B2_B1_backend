package sistema_eventos_1.backend.modules.eventos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString
@Table(name = "eventos")
public class EventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Long id;

    @Column(name = "id_cliente")
    private Long id_cliente;

    @Column(name = "id_salon")
    private Long id_salon;

    @Column(name = "nombre_evento")
    private String nombre_evento;

    @Column(name = "fecha_evento")
    private LocalDate fecha_evento;

    @Column(name = "cantidad_personas")
    private Integer cantidad_personas;

    @Column(name = "cantidad_horas")
    private Integer cantidad_horas;

    @Column(name = "estado")
    private String estado;

    @Column(name = "total_pago")
    private Double total_pago;




}
