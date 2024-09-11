package itstep.learning.services.gen;

import java.security.SecureRandom;

public class SaltGenerate implements GenerateService {
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()_+";
    private static final SecureRandom random = new SecureRandom();

    public String generate() {
        StringBuilder salt = new StringBuilder();
        int length = 16; 

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            salt.append(CHARACTERS.charAt(index));
        }

        return salt.toString();
    }
}
