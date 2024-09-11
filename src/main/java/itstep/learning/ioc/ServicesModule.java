package itstep.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import itstep.learning.services.gen.FileNameGenerate;
import itstep.learning.services.gen.OTPGenerate;
import itstep.learning.services.gen.PassGenerate;
import itstep.learning.services.gen.SaltGenerate;
import itstep.learning.services.hash.HashService;
import itstep.learning.services.hash.Md5HashService;
import itstep.learning.services.hash.ShaHashService;

public class ServicesModule extends AbstractModule {
    @Override
    protected void configure() {
        bind( HashService.class )
                .annotatedWith( Names.named("digest") )
                .to( Md5HashService.class ) ;

        bind( HashService.class )
                .annotatedWith( Names.named("signature") )
                .to( ShaHashService.class ) ;

        bind( String.class )
                .annotatedWith( Names.named("appName") )
                .toInstance( "Java-PV221" );

        bind( String.class )
                .annotatedWith( Names.named("hwLogo") )
                .toInstance( "============== HOME WORK ==============" );

        bind( OTPGenerate.class )
                .annotatedWith( Names.named("otp") )
                .to( OTPGenerate.class );

        bind( FileNameGenerate.class )
                .annotatedWith( Names.named("fileName") )
                .to( FileNameGenerate.class );

        bind( PassGenerate.class )
                .annotatedWith( Names.named("pass") )
                .to( PassGenerate.class );

        bind( SaltGenerate.class )
                .annotatedWith( Names.named("salt") )
                .to( SaltGenerate.class );

    }
}
/*
Модуль реєстрації залежностей (служб)
 */
