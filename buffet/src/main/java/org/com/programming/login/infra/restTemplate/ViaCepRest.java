package org.com.programming.login.infra.restTemplate;

import org.com.programming.login.entities.DTO.AddressResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepRest {

    private static final String URL = "https://viacep.com.br/ws/{cep}/json/";

    private final RestTemplate restTemplate;

    public ViaCepRest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AddressResponse consultaCEP(String cep){
        String cepClear = cep.replaceAll("[^0-9]", "");

        try {
            AddressResponse response = restTemplate.getForObject(
                    URL,
                    AddressResponse.class,
                    cepClear
            );
            if (response == null){
                return null;
            }
            return response;
        }catch (Exception e){
            System.out.println("Erro ao buscar o CEP: " + e.getMessage());
            return null;
        }
    }
}
