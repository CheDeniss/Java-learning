package itstep.learning.services.gen;

import com.google.inject.Singleton;

import java.util.Random;

@Singleton
public class OTPGenerate implements GenerateService {
    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 6;

        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}
