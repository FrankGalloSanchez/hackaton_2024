package pe.edu.vallegrande.supplier.rest;

import pe.edu.vallegrande.supplier.model.Supplier;
import pe.edu.vallegrande.supplier.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierRest {

    private final SupplierService supplierService;

    // Constructor para inyectar el servicio
    public SupplierRest(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // Crear un nuevo proveedor
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Supplier> createSupplier(@RequestBody Supplier supplier) {
        return supplierService.save(supplier);
    }

    // Actualizar un proveedor existente
    @PutMapping("/{id}")
    public Mono<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        supplier.setId(id); // Asegura que el ID del proveedor se establezca correctamente
        return supplierService.update(supplier);
    }

    // Eliminar un proveedor por ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteSupplier(@PathVariable Long id) {
        return supplierService.deleteById(id);
    }

    // Obtener un proveedor por ID
    @GetMapping("/{id}")
    public Mono<Supplier> getSupplierById(@PathVariable Long id) {
        return supplierService.findById(id);
    }

    // Obtener todos los proveedores
    @GetMapping
    public Flux<Supplier> getAllSuppliers() {
        return supplierService.findAll();
    }

    // Obtener proveedores por estado
    @GetMapping("/status/{status}")
    public Flux<Supplier> getSuppliersByStatus(@PathVariable Character status) {
        return supplierService.findByStatus(status);
    }

    // Inactivar un proveedor (cambiar su estado a 'I')
    @PatchMapping("/{id}/inactivate")
    public Mono<Supplier> inactivateSupplier(@PathVariable Long id) {
        return supplierService.inactivate(id);
    }

    // Activar un proveedor (cambiar su estado a 'A')
    @PatchMapping("/{id}/activate")
    public Mono<Supplier> activateSupplier(@PathVariable Long id) {
        return supplierService.activate(id);
    }
}

