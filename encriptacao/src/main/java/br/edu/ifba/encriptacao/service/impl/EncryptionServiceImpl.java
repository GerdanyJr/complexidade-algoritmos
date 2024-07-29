package br.edu.ifba.encriptacao.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.util.Base64;

import br.edu.ifba.encriptacao.service.EncryptionService;

public class EncryptionServiceImpl implements EncryptionService {
    private KeyPair keyPair;
    private static String AUDIO_PATH = "src\\main\\java\\br\\edu\\ifba\\encriptacao\\video\\ondas.mpeg";
    private static String PRIVATE_KEY_PATH = "..\\servidor\\servidor\\src\\main\\java\\br\\edu\\ifba\\redesmoveis\\servidor";
    private static String PUBLIC_KEY_PATH = "..\\cliente\\src\\main\\java\\br\\edu\\ifba\\redesmoveis\\cliente";

    public EncryptionServiceImpl() throws Exception {
        initialize();
    }

    private void initialize() throws Exception {
        byte[] audioBytes = Files.readAllBytes(Paths.get(
                AUDIO_PATH));
        SecureRandom secureRandom = new SecureRandom();
        byte[] audioFraction = new byte[secureRandom.nextInt(0, audioBytes.length - 1)];

        for (int i = 0; i < audioFraction.length; i++) {
            audioFraction[i] = audioBytes[i];
        }
        secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(audioFraction);

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(16384, secureRandom);
        this.keyPair = keyPairGenerator.generateKeyPair();
    }

    @Override
    public void saveKeyPair() throws IOException {
        String privateKey = Base64
                .getEncoder()
                .encodeToString(keyPair.getPrivate().getEncoded());
        String publicKey = Base64
                .getEncoder()
                .encodeToString(keyPair.getPublic().getEncoded());

        BufferedWriter privateKeyWriter = new BufferedWriter(
                new FileWriter(PRIVATE_KEY_PATH + "\\privateKey.txt"));
        privateKeyWriter.write(privateKey);
        privateKeyWriter.close();

        BufferedWriter publicKeyWriter = new BufferedWriter(
                new FileWriter(PUBLIC_KEY_PATH + "\\publicKey.txt"));
        publicKeyWriter.write(publicKey);
        publicKeyWriter.close();

    }
}
