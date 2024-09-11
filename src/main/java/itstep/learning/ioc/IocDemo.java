package itstep.learning.ioc;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import itstep.learning.services.gen.FileNameGenerate;
import itstep.learning.services.gen.OTPGenerate;
import itstep.learning.services.gen.PassGenerate;
import itstep.learning.services.gen.SaltGenerate;
import itstep.learning.services.hash.HashService;

import java.util.logging.Logger;

public class IocDemo {
    private final HashService digestHashService;
    private final HashService signatureHashService;
    private final String appName;

    private final String hwLogo;

    private final FileNameGenerate fileNameGenerate;
    private final SaltGenerate saltGenerate;
    private final OTPGenerate otpGenerate;
    private final PassGenerate passGenerate;

    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";

    @Inject private Logger logger;   // інжекція через поле (не рекомендується)

    @Inject
    public IocDemo(   // інжекція через конструктор (рекомендується)
                      @Named("digest") HashService digestHashService,
                      @Named("signature") HashService signatureHashService,
                      @Named("appName") String appName,
                      @Named("hwLogo") String hwLogo,
                        @Named("fileName") FileNameGenerate fileNameGenerate,
                        @Named("salt") SaltGenerate saltGenerate,
                        @Named("otp") OTPGenerate otpGenerate,
                        @Named("pass") PassGenerate passGenerate
    ) {
        this.digestHashService = digestHashService;
        this.signatureHashService = signatureHashService;
        this.appName = appName;
        this.hwLogo = hwLogo;
        this.fileNameGenerate = fileNameGenerate;
        this.saltGenerate = saltGenerate;
        this.otpGenerate = otpGenerate;
        this.passGenerate = passGenerate;
    }

    public void run() {
        System.out.println( appName );
        System.out.println( digestHashService.digest( "123" ) );
        System.out.println( signatureHashService.digest( "123" ) );
        logger.info( appName );

        System.out.println(BLUE + hwLogo + RESET);

        System.out.println( GREEN + "File name -> " + RESET + fileNameGenerate.generate() );
        System.out.println( GREEN + "Salt -> " + RESET + saltGenerate.generate() );
        System.out.println( GREEN + "OTP -> " + RESET + otpGenerate.generate() );
        System.out.println( GREEN + "Password -> " + RESET + passGenerate.generate() );
    }
}
