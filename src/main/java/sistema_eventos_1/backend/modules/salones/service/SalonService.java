package sistema_eventos_1.backend.modules.salones.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sistema_eventos_1.backend.modules.salones.dto.SalonDTO;
import sistema_eventos_1.backend.modules.salones.entity.SalonEntity;
import sistema_eventos_1.backend.modules.salones.repository.SalonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j @Service @RequiredArgsConstructor
public class SalonService {

    private final SalonRepository salonRepository;

    private SalonDTO convertirADTO(SalonEntity entity){
        SalonDTO dto = new SalonDTO();
        dto.setId(entity.getId());
        dto.setNombre_salon(entity.getNombre_salon());
        dto.setCapacidad(entity.getCapacidad());
        dto.setPrecio_renta(entity.getPrecio_renta());
        dto.setUbicacion(entity.getUbicacion());
        return  dto;
    }

    private SalonEntity convertirAEntity(SalonDTO dto){
        SalonEntity entity  = new SalonEntity();
        entity.setNombre_salon(dto.getNombre_salon());
        entity.setCapacidad(dto.getCapacidad());
        entity.setPrecio_renta(dto.getPrecio_renta());
        entity.setUbicacion(dto.getUbicacion());
        return entity;
    }

    public SalonDTO crear(SalonDTO dto){
        SalonEntity entity = convertirAEntity(dto);
        SalonEntity guardado = salonRepository.save(entity);
        return convertirADTO(guardado);
    }

    public List<SalonDTO> obtenerTodos(){
        List<SalonEntity> entities = salonRepository.findAll();
        List<SalonDTO> dtos =new ArrayList<>();
        for (SalonEntity entity : entities){
            dtos.add(convertirADTO(entity));
        }
        return dtos;
    }

    public SalonDTO obtenerPorId(Long id){
        Optional<SalonEntity> optionalSalonEntity = salonRepository.findById(id);
        if (optionalSalonEntity.isPresent()) {
        return convertirADTO(optionalSalonEntity.get());
        }
        return null;
    }

    public SalonDTO actualizar(SalonDTO dto, Long id){
        try {
            Optional<SalonEntity> optional = salonRepository.findById(id);
            if(optional.isPresent()){
                SalonEntity entity  = optional.get();
                entity.setNombre_salon(dto.getNombre_salon());
                entity.setCapacidad(dto.getCapacidad());
                entity.setPrecio_renta(dto.getPrecio_renta());
                entity.setUbicacion(dto.getUbicacion());
                SalonEntity guardar = salonRepository.save(entity);
                return convertirADTO(guardar);
            }
            return null;
        }
        catch (Exception e){
            log.error("Error al actualizar");
            return null;
        }
    }



    public boolean eliminar(Long id){
        if (salonRepository.existsById(id)){
            salonRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
