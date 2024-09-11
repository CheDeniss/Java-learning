package itstep.learning.services.gen;

import java.security.SecureRandom;

public class PassGenerate implements GenerateService {
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()-_=+[]{}|;:',.<>?/";

    public String generate() {
        StringBuilder password = new StringBuilder();
        SecureRandom sr = new SecureRandom();
        int length = sr.nextInt(10) + 8;

        for (int i = 0; i < length; i++) {
            int index = sr.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }

        return password.toString();
    }
}
