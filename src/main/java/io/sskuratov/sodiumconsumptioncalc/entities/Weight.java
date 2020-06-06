package io.sskuratov.sodiumconsumptioncalc.entities;

import java.math.BigDecimal;

public class Weight implements Entity {

    enum Units {
        GRAM("г");

        private String caption;

        Units(String caption) {
            this.caption = caption;
        }
    }

    public static final String CAPTION = "Вес";
    private BigDecimal weight;

    public static final BigDecimal MAX = new BigDecimal("300.00");
    public static final BigDecimal MIN = new BigDecimal("30.00");

    public Weight(BigDecimal weight) {
        this.weight = weight;
    }

    @Override
    public BigDecimal getValue() {
        return weight;
    }

    @Override
    public String getCaption() {
        return CAPTION;
    }

    @Override
    public Enum getUnits() {
        return Units.GRAM;
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
