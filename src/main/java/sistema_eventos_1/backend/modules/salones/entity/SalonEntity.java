package sistema_eventos_1.backend.modules.salones.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter @Getter @ToString
@Table(name = "salones")
public class SalonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salon")
    private Long id;

    @Column(name = "nombre_salon")
    private String nombre_salon;

    @Column(name = "capacidad")
    private Integer capacidad;

    @Column(name = "precio_renta")
    private Double precio_renta;

    @Column(name = "ubicacion")
    private String ubicacion;
}
