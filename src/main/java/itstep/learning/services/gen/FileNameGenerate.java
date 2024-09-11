package itstep.learning.services.gen;

import java.util.Random;

public class FileNameGenerate implements GenerateService {
    public String generate() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int length = 8;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }

        return sb.toString();    }
}
