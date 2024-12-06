package pe.edu.vallegrande.supplier.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("supplier")
public class Supplier {

    @Id
    private Long id;

    private String company;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String notes;

    private Character status = 'A';

}
