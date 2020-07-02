package io.sskuratov.sodiumconsumptioncalc.constraints;

import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;

public class CreatinineConcentration implements Constraint<Double> {
    @Override
    public void validate(Double value) throws InputException {
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

    private Double getMax() {
        return 100000.0;
    }

    private Double getMin() {
        return 0.0;
    }

    @Override
    public String toString() {
        return "CreatinineConcentration min: " + getMin() + ", max: " + getMax();
    }
}
