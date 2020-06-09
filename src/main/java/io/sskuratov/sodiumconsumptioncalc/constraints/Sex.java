package io.sskuratov.sodiumconsumptioncalc.constraints;

import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;

import java.math.BigDecimal;
import java.util.Objects;

public class Sex implements Constraint<String> {

    public Sex of(BigDecimal value) {
        Objects.requireNonNull(value);

        return new Sex();
    }

    @Override
    public void validate(String value) throws InputException {
        if (!getMax().equalsIgnoreCase(value) && !getMin().equalsIgnoreCase(value)) {
            StringBuilder builder = new StringBuilder();
            builder.append("Допустимые значения для данного параметра \"Мужской\" или \"Женский\"");
            builder.append(System.lineSeparator());

            builder.append("Возможно, Вы допустили ошибку при вводе: \"");
            builder.append(value);
            builder.append("\"");

            throw new InputException(builder.toString());
        }
    }

    @Override
    public String getMax() {
        return "Мужской";
    }

    @Override
    public String getMin() {
        return "Женский";
    }
}