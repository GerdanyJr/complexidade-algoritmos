package br.edu.ifba.redesmoveis.cliente.application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;

import com.google.gson.Gson;

import br.edu.ifba.redesmoveis.cliente.infra.client.ApiClient;
import br.edu.ifba.redesmoveis.cliente.model.entities.Consumption;
import br.edu.ifba.redesmoveis.cliente.model.entities.Person;
import br.edu.ifba.redesmoveis.cliente.model.req.RegisterReq;
import br.edu.ifba.redesmoveis.cliente.port.generator.PersonGenerator;
import br.edu.ifba.redesmoveis.cliente.useCases.Operations;

public class Client implements Runnable {

    private static final String PUBLIC_KEY_PATH = "src/main/java/br/edu/ifba/redesmoveis/cliente/publicKey.txt";
    private final ApiClient apiClient;
    private final PersonGenerator personGenerator;

    public Client(ApiClient apiClient) {
        this.apiClient = apiClient;
        this.personGenerator = new PersonGenerator();
    }

    @Override
    public void run() {
        try {
            Person person = personGenerator.generate();
            List<Consumption> consumptionPerHour = Operations.getConsumptionPerHour(person);
            RegisterReq registerReq = new RegisterReq(person, consumptionPerHour);
            Gson gson = new Gson();
            String json = gson.toJson(registerReq);

            String publicKeyBase64 = getKey();
            String encryptedJson = encrypt(publicKeyBase64, json);
            apiClient.register(encryptedJson);
            System.out.println("Registro enviado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro no registro!");
        }
    }

    private String encrypt(String publicKeyBase64, String json) throws Exception {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
        PublicKey publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyBytes));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedJsonBytes = cipher.doFinal(json.getBytes());
        return Base64.getEncoder().encodeToString(encryptedJsonBytes);
    }

    private String getKey() throws IOException {
        return new String(Files.readAllBytes(Paths.get(PUBLIC_KEY_PATH)));
    }
}