package sistema_eventos_1.backend.modules.salones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_eventos_1.backend.modules.salones.entity.SalonEntity;

public interface SalonRepository extends JpaRepository<SalonEntity, Long > {
}
