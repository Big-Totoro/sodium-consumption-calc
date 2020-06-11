package io.sskuratov.sodiumconsumptioncalc.constraints;

import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;

import java.math.BigDecimal;
import java.util.Objects;

public class CreatinineConcentrationUnits implements Constraint<String> {

    public CreatinineConcentrationUnits of(BigDecimal value) {
        Objects.requireNonNull(value);

        return new CreatinineConcentrationUnits();
    }

    @Override
    public String getMax() {
        return "";
    }

    @Override
    public String getMin() {
        return "";
    }

    @Override
    public void validate(String value) throws InputException {
        if (!("мкмоль/л".equalsIgnoreCase(value.trim())) &&
            !("ммоль/л".equalsIgnoreCase(value.trim())) &&
            !("мг/дл".equalsIgnoreCase(value.trim()))) {
            StringBuilder builder = new StringBuilder();
            builder.append("Допустимо только одно из следующих значениий для данного параметра: \"");
            builder.append("мкмоль/л, ");
            builder.append("ммоль/л, ");
            builder.append("мг/дл");
            builder.append("\"");
            builder.append(System.lineSeparator());

            builder.append("Возможно, Вы допустили ошибку при вводе: \"");
            builder.append(value);
            builder.append("\"");

            throw new InputException(builder.toString());
        }
    }

    @Override
    public String toString() {
        return "CreatinineConcentrationUnits min: " + getMin() + ", max: " + getMax();
    }
}
