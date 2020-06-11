package io.sskuratov.sodiumconsumptioncalc.constraints;

import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;

public class CreatinineConcentrationUnits implements Constraint<String> {
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

            String builder = "Допустимо только одно из следующих значениий для данного параметра: \"" +
                    "мкмоль/л, " +
                    "ммоль/л, " +
                    "мг/дл" +
                    "\"" +
                    System.lineSeparator() +
                    "Возможно, Вы допустили ошибку при вводе: \"" +
                    value +
                    "\"";
            throw new InputException(builder);
        }
    }

    @Override
    public String toString() {
        return "CreatinineConcentrationUnits min: " + getMin() + ", max: " + getMax();
    }
}
