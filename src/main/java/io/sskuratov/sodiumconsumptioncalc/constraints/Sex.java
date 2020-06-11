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

            String builder = "Допустимые значения для данного параметра \"Мужской\" или \"Женский\"" +
                    System.lineSeparator() +
                    "Возможно, Вы допустили ошибку при вводе: \"" +
                    value +
                    "\"";
            throw new InputException(builder);
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

    @Override
    public String toString() {
        return "Sex min: " + getMin() + ", max: " + getMax();
    }

}