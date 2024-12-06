package pe.edu.vallegrande.supplier.service;

import pe.edu.vallegrande.supplier.model.Supplier;
import pe.edu.vallegrande.supplier.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    // Constructor para inyectar el repositorio
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    // Método para guardar un nuevo proveedor
    public Mono<Supplier> save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // Método para actualizar un proveedor existente
    public Mono<Supplier> update(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    // Método para eliminar un proveedor por ID
    public Mono<Void> deleteById(Long id) {
        return supplierRepository.deleteById(id);
    }

    // Método para encontrar un proveedor por ID
    public Mono<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    // Método para obtener todos los proveedores
    public Flux<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    // Método para obtener proveedores por su estado
    public Flux<Supplier> findByStatus(Character status) {
        return supplierRepository.findByStatus(status);
    }

    // Método para inactivar un proveedor (cambiar el estado a 'I')
    public Mono<Supplier> inactivate(Long id) {
        return supplierRepository.findById(id)
                .flatMap(supplier -> {
                    supplier.setStatus('I'); // Cambia el estado a 'I' para inactivar
                    return supplierRepository.save(supplier); // Guarda el proveedor con el nuevo estado
                });
    }

    // Método para activar un proveedor (cambiar el estado a 'A')
    public Mono<Supplier> activate(Long id) {
        return supplierRepository.findById(id)
                .flatMap(supplier -> {
                    supplier.setStatus('A'); // Cambia el estado a 'A' para activar
                    return supplierRepository.save(supplier); // Guarda el proveedor con el nuevo estado
                });
    }
}

