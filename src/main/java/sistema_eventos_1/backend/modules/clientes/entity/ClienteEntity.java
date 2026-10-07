package sistema_eventos_1.backend.modules.clientes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter @Getter @ToString
@Table(name = "clientes")
public class ClienteEntity {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id_cliente")
    private Long id;

     @Column(name = "nombre")
    private String nombre;

     @Column(name = "apellido")
     private String apellido;

     @Column(name = "telefono")
     private String telefono;

     @Column(name = "email")
     private String email;

     @Column(name = "direccion")
     private String direccion;




}
