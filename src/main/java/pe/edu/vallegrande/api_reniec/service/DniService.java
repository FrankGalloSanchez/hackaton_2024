package pe.edu.vallegrande.api_reniec.service;

import org.springframework.stereotype.Service;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Service
@Slf4j
public class DniService {

    private final OkHttpClient client;

    public DniService() {
        this.client = new OkHttpClient();
    }

    public String getDniInfo(String dni) {
        String url = "https://dniruc.apisperu.com/api/v1/dni/" + dni + "?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Imp1bGlvLnF1aXNwZUB2YWxsZWdyYW5kZS5lZHUucGUifQ.6M-P2QMMvKFZEeMvTUXvkOooM02N_pWqt0OdlaYW3PM";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                log.error("Unexpected code " + response);
                return null;
            }
            return response.body().string();
        } catch (IOException e) {
            log.error("Error while fetching DNI info", e);
            return null;
        }
    }
}