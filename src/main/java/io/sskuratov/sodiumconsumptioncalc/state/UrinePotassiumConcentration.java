package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.constraints.Constraint;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;
import io.sskuratov.sodiumconsumptioncalc.statemachine.States;

import java.math.BigDecimal;
import java.util.Optional;

public class UrinePotassiumConcentration implements State<BigDecimal> {

    private BigDecimal value;
    private final Constraint<BigDecimal> constraint;

    public UrinePotassiumConcentration(Constraint<BigDecimal> constraint) {
        this.constraint = constraint;
    }

    @Override
    public States getId() {
        return States.URINE_POTASSIUM_CONCENTRATION;
    }

    @Override
    public Optional<BigDecimal> get() {
        return Optional.ofNullable(value);
    }

    @Override
    public void set(BigDecimal units) {
        this.value = units;
    }

    @Override
    public States next() {
        return States.SEX;
    }

    @Override
    public void parseValue(String value) {
        this.value = new BigDecimal(value.trim().replace(",", "."));
    }

    @Override
    public void validate() throws InputException {
        constraint.validate(value);
    }
}
