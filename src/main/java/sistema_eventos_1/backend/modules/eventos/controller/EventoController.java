package sistema_eventos_1.backend.modules.eventos.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_eventos_1.backend.modules.clientes.dto.ClienteDTO;
import sistema_eventos_1.backend.modules.eventos.dto.EventoDTO;
import sistema_eventos_1.backend.modules.eventos.service.EventoService;
import sistema_eventos_1.backend.response.ApiResponse;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/eventos")
@CrossOrigin
public class EventoController {

    private final EventoService service;

    public EventoController(EventoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EventoDTO>> crear(@Valid @RequestBody EventoDTO dto){
        try{
            EventoDTO dto1 = service.crear(dto);
            ApiResponse<EventoDTO> response = new ApiResponse<>(true, "Creado",dto1);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<EventoDTO> response = new ApiResponse<>(false, "Hubo un error");
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<EventoDTO>>> obtenerTodas(){
        try {
            List<EventoDTO> list = service.obtenerTodos();
            ApiResponse<List<EventoDTO>> response = new ApiResponse<>(true,"Datos Obtenidos",list);
            return ResponseEntity.ok(response);
        }
        catch (Exception e ){
            e.printStackTrace();
            ApiResponse<List<EventoDTO>> response = new ApiResponse<>(false, "Hubo un error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EventoDTO>> obternerId(@PathVariable Long id){
        try{
            EventoDTO dto = service.obtenerPorID(id);
            if (dto != null){
                ApiResponse<EventoDTO> dtoApiResponse = new ApiResponse<>(true, "Dato obtenido",dto);
                return ResponseEntity.ok(dtoApiResponse);
            }
            ApiResponse<EventoDTO> response = new ApiResponse<>(false,"Dato No Encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e){
            e.printStackTrace();
            ApiResponse<EventoDTO> response = new ApiResponse<>(false,"No Encontrado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EventoDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody EventoDTO dto
    ){
        try {
            EventoDTO dto1 = service.actualizar(dto,id);
            if(dto1 != null){
                ApiResponse<EventoDTO> dtoApiResponse = new ApiResponse<>(true, "Actualizado",dto1);
                return ResponseEntity.ok(dtoApiResponse);
            }
            ApiResponse<EventoDTO> response = new ApiResponse<>(false,"Dato No Encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e){
            e.printStackTrace();
            ApiResponse<EventoDTO> response = new ApiResponse<>(false,"Error al actualizar");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR
            ).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id){
        try {
            boolean elimar = service.eliminar(id);
            if (elimar){
                ApiResponse<Void> response = new ApiResponse<>(true, "Eliminado");
                return  ResponseEntity.ok(response);
            }
            ApiResponse<Void> response = new ApiResponse<>(false, "No encontrado");
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e){
            e.printStackTrace();
            ApiResponse<Void> response = new ApiResponse<>(false, "Error al eliminar");
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
