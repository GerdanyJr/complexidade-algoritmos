package br.edu.ifba.redesmoveis.cliente;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import com.google.gson.Gson;
import br.edu.ifba.redesmoveis.cliente.infra.client.ApiClient;
import br.edu.ifba.redesmoveis.cliente.model.entities.Consumption;
import br.edu.ifba.redesmoveis.cliente.model.entities.Person;
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

    // complexidade quadrática, pois chama o método getConsumptionPerHour que por sua vez tem complexidade quadrática
    @Override
    public void run() {
        try {
            Person person = personGenerator.generate();
            List<Consumption> consumptionPerHour = Operations.getConsumptionPerHour(person);
            Gson gson = new Gson();
            String json = gson.toJson(consumptionPerHour);

            // Generate AES key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            SecretKey aesKey = keyGenerator.generateKey();

            // Encrypt the JSON data with AES
            byte[] encryptedData = encryptWithAES(json, aesKey);

            // Encrypt the AES key with RSA
            String publicKeyBase64 = getKey();
            PublicKey publicKey = getPublicKeyFromBase64(publicKeyBase64);
            byte[] encryptedAesKey = encryptWithRSA(aesKey.getEncoded(), publicKey);

            // Prepare the payload with the encrypted AES key and encrypted data
            String payload = Base64.getEncoder().encodeToString(encryptedAesKey) + ":"
                    + Base64.getEncoder().encodeToString(encryptedData);

            // Send the payload to the server
            apiClient.register(payload);
            System.out.println("Registro enviado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro no registro!");
        }
    }

    private byte[] encryptWithAES(String data, SecretKey aesKey) throws Exception {
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);
        return aesCipher.doFinal(data.getBytes());
    }

    private byte[] encryptWithRSA(byte[] data, PublicKey publicKey) throws Exception {
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        rsaCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return rsaCipher.doFinal(data);
    }

    private PublicKey getPublicKeyFromBase64(String publicKeyBase64) throws Exception {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    private String getKey() throws IOException {
        return new String(Files.readAllBytes(Paths.get(PUBLIC_KEY_PATH)));
    }
}