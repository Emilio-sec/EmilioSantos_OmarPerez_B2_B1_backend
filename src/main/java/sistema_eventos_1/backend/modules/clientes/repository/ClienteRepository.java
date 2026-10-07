package sistema_eventos_1.backend.modules.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_eventos_1.backend.modules.clientes.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
