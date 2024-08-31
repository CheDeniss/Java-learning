package itstep.learning.oop;

import java.util.Locale;

@Warranty
public class Lamp
        extends Product
        implements Testable
         {

    private double power;

    public Lamp( String manufacturer, double power ) {
        super.setManufacturer( manufacturer );
        this.setPower( power );
    }

    @Override
    public String getCard() {
        Warranty w = this.getClass().getAnnotation(Warranty.class);

        return String.format(
                Locale.ROOT,
                "Lamp: '%s', Power: %.1f W, %s",
                super.getManufacturer(), this.getPower(), w.warrantyPeriod() == 0 ? "No warranty" : "Warranty period: " + w.warrantyPeriod() + " months"
        );
    }

    public double getPower() {
        return power;
    }

    public void setPower( double power ) {
        if( power < 0 ) {
            throw new RuntimeException( "Negative power" );
        }
        this.power = power;
    }

    @Override
    public void test() {
        System.out.println("Testing: " + getCard());
    }

    @Works("as lamp")
    public void shine() {
        System.out.println("Working on: " + getCard());
    }
}
