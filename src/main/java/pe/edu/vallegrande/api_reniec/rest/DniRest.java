package pe.edu.vallegrande.api_reniec.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.vallegrande.api_reniec.service.DniService;

@RestController
public class DniRest {

    @Autowired
    private DniService dniService;

    @GetMapping("/dni/{dni}")
    public String getDniInfo(@PathVariable String dni) {
        String dniInfo = dniService.getDniInfo(dni);
        return dniInfo;
    }
}