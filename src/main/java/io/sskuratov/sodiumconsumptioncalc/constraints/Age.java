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
            StringBuilder builder = new StringBuilder();
            builder.append("Допустимые значения для данного параметра находятся в интервале от ");
            builder.append(getMin());
            builder.append(" до ");
            builder.append(getMax());
            builder.append(System.lineSeparator());

            builder.append("Возможно, Вы допустили ошибку при вводе числа: \"");
            builder.append(value);
            builder.append("\"");

            throw new InputException(builder.toString());
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
}
