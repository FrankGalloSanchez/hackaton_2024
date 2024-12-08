package pe.edu.vallegrande.api_reniec.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import pe.edu.vallegrande.api_reniec.model.Dni;
import pe.edu.vallegrande.api_reniec.repository.DniRepository;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DniService {

    private final WebClient webClient;
    private final DniRepository dniRepository;

    public DniService(WebClient.Builder webClientBuilder, DniRepository dniRepository) {
        this.webClient = webClientBuilder.baseUrl("https://dniruc.apisperu.com/api/v1").build();
        this.dniRepository = dniRepository;
    }

    public Mono<Dni> getDniInfo(Long dni) {
        String url = "/dni/" + dni
                + "?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imp1bGlvLnF1aXNwZUB2YWxsZWdyYW5kZS5lZHUucGUifQ.6M-P2QMMvKFZEeMvTUXvkOooM02N_pWqt0OdlaYW3PM";
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Dni.class)
                .flatMap(dniInfo -> {
                    dniInfo.setDni(dni.intValue()); // Ajustar el campo `dni`
                    return dniRepository.save(dniInfo); // Guardar en la base de datos
                })
                .doOnError(e -> log.error("Error while fetching DNI info", e));
    }

    public Mono<Void> deleteDni(Long id) {
        return dniRepository.deleteById(id);
    }

    public Mono<Dni> getDniById(Long id) {
        return dniRepository.findById(id);
    }

}