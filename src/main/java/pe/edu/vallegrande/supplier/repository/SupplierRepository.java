package pe.edu.vallegrande.supplier.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.edu.vallegrande.supplier.model.Supplier;
import reactor.core.publisher.Flux;

public interface SupplierRepository extends ReactiveCrudRepository<Supplier, Long> {
    // Método para obtener proveedores por su estado
    Flux<Supplier> findByStatus(Character status);

}
