package io.sskuratov.sodiumconsumptioncalc.constraints;

import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;

import java.math.BigDecimal;
import java.util.Objects;

public class Age implements Constraint<BigDecimal> {

    public Age of(BigDecimal value) {
        Objects.requireNonNull(value);

        return new Age();
    }

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
        return BigDecimal.valueOf(150L);
    }

    @Override
    public BigDecimal getMin() {
        return BigDecimal.ONE;
    }

    @Override
    public String toString() {
        return "Age min: " + getMin() + ", max: " + getMax();
    }
}
