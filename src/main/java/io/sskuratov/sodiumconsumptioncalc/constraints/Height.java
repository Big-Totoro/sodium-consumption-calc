package io.sskuratov.sodiumconsumptioncalc.constraints;

import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;

import java.math.BigDecimal;

public class Height implements Constraint<BigDecimal> {
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

    @Override
    public BigDecimal getMax() {
        return BigDecimal.valueOf(300L);
    }

    @Override
    public BigDecimal getMin() {
        return BigDecimal.valueOf(30L);
    }

    @Override
    public String toString() {
        return "Height min: " + getMin() + ", max: " + getMax();
    }

}
