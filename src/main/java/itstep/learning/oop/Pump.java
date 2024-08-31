package itstep.learning.oop;

import java.util.Locale;

@Warranty(warrantyPeriod = 12)
public class Pump
        extends Product
        implements Manual {

    int productivity;

    public Pump( String manufacturer, int productivity ) {
        super.setManufacturer( manufacturer );
        this.setProductivity( productivity );
    }

    @Override
    public String getCard() {
        Warranty w = this.getClass().getAnnotation(Warranty.class);

        return String.format(
                Locale.ROOT,
                "Pump: '%s', Productivity: %d l/h, %s",
                super.getManufacturer(), this.getProductivity(), w.warrantyPeriod() == 0 ? "No warranty" : "Warranty period: " + w.warrantyPeriod() + " months"
        );
    }

    public int getProductivity() {
        return productivity;
    }

    public void setProductivity( int productivity ) {
        this.productivity = productivity;
    }

    @Works("as pump")
    public void pump() {
        System.out.println("Working on: " + getCard());
    }
}
