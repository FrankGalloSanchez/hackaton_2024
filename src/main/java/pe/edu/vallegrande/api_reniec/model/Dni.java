package pe.edu.vallegrande.api_reniec.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
    
@Data
@Table("dni")
public class Dni {
    @Id
    private Long id;
    private Boolean success;
    private String dni;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Long codVerifica;
}