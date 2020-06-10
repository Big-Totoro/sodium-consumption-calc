package io.sskuratov.sodiumconsumptioncalc.entities;

import java.math.BigDecimal;

public class SodiumConcentration implements Entity {

    enum Units {
        MMOLE("ммоль/л");

        private String caption;

        Units(String caption) {
            this.caption = caption;
        }
    }

    public static final String CAPTION = "Концентрация натрия в разовой порции мочи";
    private BigDecimal concentration;

    public static final BigDecimal MAX = new BigDecimal("100000.00");
    public static final BigDecimal MIN = BigDecimal.ZERO;

    public SodiumConcentration(BigDecimal concentration) {
        this.concentration = concentration;
    }

    @Override
    public BigDecimal getValue() {
        return concentration;
    }

    @Override
    public String getCaption() {
        return CAPTION;
    }

    @Override
    public Enum getUnits() {
        return Units.MMOLE;
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
