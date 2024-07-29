package br.edu.ifba.redesmoveis.servidor.infra.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.edu.ifba.redesmoveis.servidor.infra.repository.PersonRepository;
import br.edu.ifba.redesmoveis.servidor.infra.response.PeakTime;
import br.edu.ifba.redesmoveis.servidor.infra.service.PersonService;
import br.edu.ifba.redesmoveis.servidor.model.entities.Consumption;
import br.edu.ifba.redesmoveis.servidor.model.req.RegisterReq;
import br.edu.ifba.redesmoveis.servidor.useCases.Operations;
import br.edu.ifba.redesmoveis.servidor.util.ConsumptionUtil;

@Service
public class PersonServiceImpl implements PersonService {

    private static String PRIVATE_KEY_PATH = "servidor/src/main/java/br/edu/ifba/redesmoveis/servidor/privateKey.txt";
    private final PersonRepository personRepository;
    private final Operations operations;
    private final List<Consumption> consumptions;

    public PersonServiceImpl(PersonRepository personRepository, Operations operations) {
        this.personRepository = personRepository;
        this.operations = operations;
        consumptions = ConsumptionUtil.fillConsumptions();
    }

    // complexidade quadrática O(n^2) pois possui dois laços aninhados
    @Override
    public void register(String payload) {
        RegisterReq registerReq;
        try {
            registerReq = decrypt(payload);
            System.out.println("Registrando pessoa com nome " +
                    registerReq.person().getName());
            personRepository.save(registerReq.person());

            var consumptionPerHour = registerReq.consumptionPerHour();

            for (int i = 0; i < consumptionPerHour.size(); i++) {
                for (int j = 0; j < consumptions.size(); j++) {
                    Consumption consumption = consumptions.get(j);

                    if (consumptionPerHour.get(i).getHourOfDay() == consumption.getHourOfDay()) {
                        consumption
                                .setConsumption(
                                        consumption.getConsumption() +
                                                consumptionPerHour.get(i).getConsumption());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RegisterReq decrypt(String payload) throws Exception {
        String privateKeyBase64 = getKey();
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);

        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
        byte[] encryptedJsonBytes = Base64.getDecoder().decode(payload);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedJsonBytes = cipher.doFinal(encryptedJsonBytes);
        String decryptedJson = new String(decryptedJsonBytes);

        Gson gson = new Gson();
        System.out.println(decryptedJson);
        RegisterReq result = gson.fromJson(decryptedJson, RegisterReq.class);
        return result;
    }

    @Override
    public PeakTime getPeakTime() {
        return operations.findPeakTime(consumptions);
    }

    private String getKey() throws IOException {
        String key = "";
        key = new String(Files.readAllBytes(Paths.get(PRIVATE_KEY_PATH)));
        return key;
    }

}
