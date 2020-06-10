package io.sskuratov.sodiumconsumptioncalc.entities;

import java.math.BigDecimal;

public class Height implements Entity {

    enum Units {
        CM("см");

        private String caption;

        Units(String caption) {
            this.caption = caption;
        }
    }

    public static final String CAPTION = "Рост";
    private BigDecimal height;

    public static final BigDecimal MAX = new BigDecimal("300.00");
    public static final BigDecimal MIN = new BigDecimal("30.00");

    public Height(BigDecimal height) {
        this.height = height;
    }

    @Override
    public BigDecimal getValue() {
        return height;
    }

    @Override
    public String getCaption() {
        return CAPTION;
    }

    @Override
    public Enum getUnits() {
        return Units.CM;
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
