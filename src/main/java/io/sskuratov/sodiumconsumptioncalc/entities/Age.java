package io.sskuratov.sodiumconsumptioncalc.entities;

import java.math.BigDecimal;

public class Age implements Entity {

    public enum Units {
        YEARS("лет");

        private String caption;

        Units(String caption) {
            this.caption = caption;
        }
    }

    public static final String CAPTION = "Возраст";
    private BigDecimal age;

    public static final BigDecimal MAX = new BigDecimal("150.00");
    public static final BigDecimal MIN = BigDecimal.ONE;

    public Age(BigDecimal age) {
        this.age = age;
    }

    @Override
    public BigDecimal getValue() {
        return age;
    }

    public String getCaption() {
        return CAPTION;
    }

    @Override
    public Enum getUnits() {
        return Units.YEARS;
    }

    @Override
    public BigDecimal getMax() {
        return MAX;
    }

    @Override
    public BigDecimal getMin() {
        return MIN;
    }
}
