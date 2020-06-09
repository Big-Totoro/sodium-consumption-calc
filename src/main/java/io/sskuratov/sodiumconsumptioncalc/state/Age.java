package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.constraints.Constraint;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;

import java.math.BigDecimal;
import java.util.Optional;

public class Age implements State<BigDecimal> {

    private BigDecimal value;
    private final Constraint<BigDecimal> constraint;

    public Age(Constraint<BigDecimal> constraint) {
        this.constraint = constraint;
    }

    @Override
    public States getId() {
        return States.AGE;
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
    public void parseValue(String value) {
        this.value = new BigDecimal(value.trim().replace(",", "."));
    }

    @Override
    public States next() {
        return States.HEIGHT;
    }

    @Override
    public void validate() throws InputException {
        constraint.validate(value);
    }
}