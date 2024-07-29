package br.edu.ifba.redesmoveis.cliente.infra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8080/person", name = "redesMoveis")
public interface ApiClient {
    @PostMapping("/register")
    public void register(@RequestBody String data);
}
