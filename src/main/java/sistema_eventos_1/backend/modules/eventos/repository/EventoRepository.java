package sistema_eventos_1.backend.modules.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_eventos_1.backend.modules.eventos.entity.EventoEntity;

public interface EventoRepository extends JpaRepository<EventoEntity, Long> {
}
