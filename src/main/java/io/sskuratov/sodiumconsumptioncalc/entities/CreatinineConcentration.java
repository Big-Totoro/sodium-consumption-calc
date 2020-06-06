package io.sskuratov.sodiumconsumptioncalc.entities;

import java.math.BigDecimal;

public class CreatinineConcentration implements Entity {

    enum Units {
        MKMOLE("мкмоль/л"),
        MMOLE("ммоль/л"),
        MGRAM("мг/дл");

        private String caption;

        Units(String caption) {
            this.caption = caption;
        }
    }

    public static final String CAPTION = "Концентрация креатинина в разовой порции мочи";
    private BigDecimal concentration;

    public static final BigDecimal MAX = new BigDecimal("100000.00");
    public static final BigDecimal MIN = BigDecimal.ZERO;

    public CreatinineConcentration(BigDecimal concentration) {
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
        return Units.MKMOLE;
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