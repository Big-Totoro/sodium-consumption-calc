package io.sskuratov.sodiumconsumptioncalc.entities;

import java.math.BigDecimal;

public class Sex implements Entity {

    public enum Units {
        MALE("Мужской"), FEMALE("Женский");

        private String caption;

        Units(String caption) {
            this.caption = caption;
        }
    }

    public static final String CAPTION = "Пол";
    private Units sex;

    public Sex(Units sex) {
        this.sex = sex;
    }

    @Override
    public BigDecimal getValue() {
        return BigDecimal.valueOf(sex.ordinal());
    }

    @Override
    public String getCaption() {
        return CAPTION;
    }

    @Override
    public Enum getUnits() {
        return Units.MALE;
    }

    @Override
    public BigDecimal getMax() {
        return BigDecimal.ONE;
    }

    @Override
    public BigDecimal getMin() {
        return BigDecimal.ZERO;
    }
}
