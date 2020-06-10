package io.sskuratov.sodiumconsumptioncalc.entities;

import java.math.BigDecimal;

public class Init implements Entity {

    enum Units {
        BEGIN(CAPTION);

        private String caption;

        Units(String caption) {
            this.caption = caption;
        }
    }

    public static final String CAPTION = "Начало";

    public static final BigDecimal MAX = BigDecimal.ONE;
    public static final BigDecimal MIN = BigDecimal.ZERO;

    public Init() {
    }

    @Override
    public BigDecimal getValue() {
        return BigDecimal.ZERO;
    }

    @Override
    public String getCaption() {
        return CAPTION;
    }

    @Override
    public Enum getUnits() {
        return Units.BEGIN;
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
