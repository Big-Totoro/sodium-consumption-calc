package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.constraints.Constraint;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;

import java.util.Optional;

public class UrineCreatinineConcentrationUnits implements State<String> {

    private String units;
    private final Constraint<String> constraint;

    public UrineCreatinineConcentrationUnits(Constraint<String> constraint) {
        this.constraint = constraint;
    }

    @Override
    public States getId() {
        return States.URINE_CREATININE_CONCENTRATION_UNITS;
    }

    @Override
    public Optional<String> get() {
        return Optional.ofNullable(units);
    }

    @Override
    public void set(String units) {
        this.units = units;
    }

    @Override
    public void parseValue(String value) {
        this.units = value;
    }

    @Override
    public States next() {
        return States.URINE_SODIUM_CONCENTRATION;
    }

    @Override
    public void validate() throws InputException {
        constraint.validate(units);
    }
}
