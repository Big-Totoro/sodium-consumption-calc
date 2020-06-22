package io.sskuratov.sodiumconsumptioncalc.constraints;

import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;

import java.math.BigDecimal;

public class SodiumConcentration implements Constraint<BigDecimal> {
    @Override
    public void validate(BigDecimal value) throws InputException {
        if ((value.compareTo(getMax()) > 0) || (value.compareTo(getMin()) < 0)) {

            String builder = "Допустимые значения для данного параметра находятся в интервале от " +
                    getMin() +
                    " до " +
                    getMax() +
                    System.lineSeparator() +
                    "Возможно, Вы допустили ошибку при вводе числа: \"" +
                    value +
                    "\"";
            throw new InputException(builder);
        }
    }

    private BigDecimal getMax() {
        return BigDecimal.valueOf(100000L);
    }

    private BigDecimal getMin() {
        return BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "SodiumConcentration min: " + getMin() + ", max: " + getMax();
    }
}