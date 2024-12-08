package pe.edu.vallegrande.api_reniec.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import pe.edu.vallegrande.api_reniec.model.Dni;
import pe.edu.vallegrande.api_reniec.service.DniService;
import reactor.core.publisher.Mono;

@RestController
public class DniRest {

    @Autowired
    private DniService dniService;

    @GetMapping("/dni/{dni}")
    public Mono<Dni> getDniInfo(@PathVariable Long dni) {
        return dniService.getDniInfo(dni);
    }

    @DeleteMapping("/dni/{id}")
    public Mono<Void> deleteDni(@PathVariable Long id) {
        return dniService.deleteDni(id);
    }

    @GetMapping("/dni/id/{id}")
    public Mono<Dni> getDniById(@PathVariable Long id) {
        return dniService.getDniById(id);
    }
}