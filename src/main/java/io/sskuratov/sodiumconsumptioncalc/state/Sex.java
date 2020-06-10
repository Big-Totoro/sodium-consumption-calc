package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.constraints.Constraint;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;
import io.sskuratov.sodiumconsumptioncalc.statemachine.States;

import java.util.Optional;

public class Sex implements State<String> {

    private String sex;
    private final Constraint<String> constraint;

    public Sex(Constraint<String> constraint) {
        this.constraint = constraint;
    }

    @Override
    public States getId() {
        return States.SEX;
    }

    @Override
    public Optional<String> get() {
        return Optional.ofNullable(sex);
    }

    @Override
    public void set(String units) {
        this.sex = units;
    }

    @Override
    public void parseValue(String value) {
        this.sex = value;
    }

    @Override
    public States next() {
        return States.AGE;
    }

    @Override
    public void validate() throws InputException {
        constraint.validate(sex);
    }
}
