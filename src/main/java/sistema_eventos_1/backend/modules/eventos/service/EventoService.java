package sistema_eventos_1.backend.modules.eventos.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sistema_eventos_1.backend.modules.eventos.dto.EventoDTO;
import sistema_eventos_1.backend.modules.eventos.entity.EventoEntity;
import sistema_eventos_1.backend.modules.eventos.repository.EventoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j @Service @RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    private EventoDTO convertirADTO(EventoEntity entity){
        EventoDTO dto = new EventoDTO();
        dto.setId(entity.getId());
        dto.setId_cliente(entity.getId_cliente());
        dto.setId_salon(entity.getId_salon());
        dto.setNombre_salon(entity.getNombre_evento());
        dto.setFecha_evento(entity.getFecha_evento());
        dto.setCantidad_personas(entity.getCantidad_personas());
        dto.setCantidad_horas(entity.getCantidad_horas());
        dto.setEstado(entity.getEstado());
        dto.setTotal_pago(entity.getTotal_pago());
        return dto;
    }

    private EventoEntity convertirAEntity(EventoDTO dto){
        EventoEntity entity = new EventoEntity();
        entity.setId_cliente(dto.getId_cliente());
        entity.setId_salon(dto.getId_salon());
        entity.setNombre_evento(entity.getNombre_evento());
        entity.setFecha_evento(entity.getFecha_evento());
        entity.setCantidad_personas(entity.getCantidad_personas());
        entity.setCantidad_horas(entity.getCantidad_horas());
        entity.setEstado(entity.getEstado());
        entity.setTotal_pago(entity.getTotal_pago());
        return entity;
    }

    public EventoDTO crear(EventoDTO dto){
        EventoEntity entity = convertirAEntity(dto);
        EventoEntity guardar = eventoRepository.save(entity);
        return convertirADTO(guardar);
    }

    public List<EventoDTO> obtenerTodos(){
        List<EventoEntity> entities = eventoRepository.findAll();
        List<EventoDTO> dtos = new ArrayList<>();
        for (EventoEntity entity : entities){
            dtos.add(convertirADTO(entity));
        }
        return dtos;
    }

    public  EventoDTO obtenerPorID(Long id){
        Optional<EventoEntity> optional = eventoRepository.findById(id);
        if(optional.isPresent()){
            return  convertirADTO(optional.get());
        }
        return null;
    }

    public EventoDTO actualizar(EventoDTO dto , Long id){
        try{
            Optional<EventoEntity> optional = eventoRepository.findById(id);
            if (optional.isPresent()){
                EventoEntity entity = optional.get();
                entity.setId_cliente(dto.getId_cliente());
                entity.setId_salon(dto.getId_salon());
                entity.setNombre_evento(entity.getNombre_evento());
                entity.setFecha_evento(entity.getFecha_evento());
                entity.setCantidad_personas(entity.getCantidad_personas());
                entity.setCantidad_horas(entity.getCantidad_horas());
                entity.setEstado(entity.getEstado());
                entity.setTotal_pago(entity.getTotal_pago());
                EventoEntity guardar = eventoRepository.save(entity);
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
        if(eventoRepository.existsById(id)){
            eventoRepository.deleteById(id);
            return  true;
        }
        return false;
    }

}
