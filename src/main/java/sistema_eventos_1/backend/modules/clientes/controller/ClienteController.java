package sistema_eventos_1.backend.modules.clientes.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema_eventos_1.backend.modules.clientes.dto.ClienteDTO;
import sistema_eventos_1.backend.modules.clientes.service.ClienteService;
import sistema_eventos_1.backend.response.ApiResponse;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/clientes")
@CrossOrigin
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClienteDTO>> crear(@Valid @RequestBody ClienteDTO dto){
        try{
            ClienteDTO dto1 = clienteService.crear(dto);
            ApiResponse<ClienteDTO> response = new ApiResponse<>(true, "Creado",dto1);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse<ClienteDTO> response = new ApiResponse<>(false, "Hubo un error");
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClienteDTO>>> obtenerTodas(){
        try {
            List<ClienteDTO> list = clienteService.obtenerTodos();
            ApiResponse<List<ClienteDTO>> response = new ApiResponse<>(true,"Datos Obtenidos",list);
            return ResponseEntity.ok(response);
        }
        catch (Exception e ){
            e.printStackTrace();
            ApiResponse<List<ClienteDTO>> response = new ApiResponse<>(false, "Hubo un error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteDTO>> obternerId(@PathVariable Long id){
        try{
            ClienteDTO dto = clienteService.obtenerPorId(id);
            if (dto != null){
                ApiResponse<ClienteDTO> dtoApiResponse = new ApiResponse<>(true, "Dato obtenido",dto);
                return ResponseEntity.ok(dtoApiResponse);
            }
            ApiResponse<ClienteDTO> response = new ApiResponse<>(false,"Dato No Encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e){
            e.printStackTrace();
            ApiResponse<ClienteDTO> response = new ApiResponse<>(false,"No Encontrado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteDTO>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ClienteDTO dto
    ){
        try {
            ClienteDTO dto1 = clienteService.actualizar(id,dto);
            if(dto1 != null){
                ApiResponse<ClienteDTO> dtoApiResponse = new ApiResponse<>(true, "Actualizado",dto1);
                return ResponseEntity.ok(dtoApiResponse);
            }
            ApiResponse<ClienteDTO> response = new ApiResponse<>(false,"Dato No Encontrado !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        catch (Exception e){
            e.printStackTrace();
            ApiResponse<ClienteDTO> response = new ApiResponse<>(false,"Error al actualizar");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR
            ).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id){
        try {
            boolean elimar = clienteService.eliminar(id);
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
