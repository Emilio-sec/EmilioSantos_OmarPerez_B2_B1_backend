package sistema_eventos_1.backend.modules.clientes.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Cluster;
import org.springframework.cglib.core.ClassesKey;
import org.springframework.stereotype.Service;
import sistema_eventos_1.backend.modules.clientes.dto.ClienteDTO;
import sistema_eventos_1.backend.modules.clientes.entity.ClienteEntity;
import sistema_eventos_1.backend.modules.clientes.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j @Service @RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private ClienteDTO convertirADTO(ClienteEntity entity){
        ClienteDTO dto = new ClienteDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setTelefono(entity.getTelefono());
        dto.setEmail(entity.getEmail());
        dto.setDireccion(entity.getDireccion());
        return dto;
    }

    private ClienteEntity convertirAEntity(ClienteDTO dto){
        ClienteEntity entity = new ClienteEntity();
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setTelefono(dto.getTelefono());
        entity.setEmail(dto.getEmail());
        entity.setDireccion(dto.getDireccion());
        return entity;
    }

    public ClienteDTO crear(ClienteDTO dto){
        ClienteEntity entity = convertirAEntity(dto);
        ClienteEntity guardar = clienteRepository.save(entity);
        return convertirADTO(guardar);
    }

    public List<ClienteDTO> obtenerTodos(){
        List<ClienteEntity>entities = clienteRepository.findAll();
        List<ClienteDTO> dtos = new ArrayList<>();
        for (ClienteEntity entity : entities){
            dtos.add(convertirADTO(entity));
        }
        return dtos;
    }

    public ClienteDTO obtenerPorId(Long id){
        Optional<ClienteEntity> optionalClienteEntity = clienteRepository.findById(id);
        if (optionalClienteEntity.isPresent()){
            return convertirADTO(optionalClienteEntity.get());
        }
        return null;
    }

    public ClienteDTO actualizar(Long id, ClienteDTO dto){
        try{
            Optional<ClienteEntity> optionalClienteEntity = clienteRepository.findById(id);
            if (optionalClienteEntity.isPresent()){
                ClienteEntity entity = optionalClienteEntity.get();
                entity.setNombre(dto.getNombre());
                entity.setApellido(dto.getApellido());
                entity.setTelefono(dto.getTelefono());
                entity.setEmail(dto.getEmail());
                entity.setDireccion(dto.getDireccion());
                ClienteEntity guardar = clienteRepository.save(entity);
                return convertirADTO(guardar);
            }
            return null;
        } catch (Exception e) {
            log.error("Error al actualizar");
            return null;
        }
    }

    public boolean eliminar(Long id){
        if (clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
