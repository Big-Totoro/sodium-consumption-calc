package io.sskuratov.sodiumconsumptioncalc.state;

import java.util.Optional;

public class InitState implements State<String> {

    @Override
    public States getId() {
        return States.INIT;
    }

    @Override
    public Optional<String> get() {
        return Optional.empty();
    }

    @Override
    public void set(String value) {

    }

    @Override
    public void parseValue(String value) {

    }

    @Override
    public States next() {
        return States.URINE_CREATININE_CONCENTRATION;
    }

    @Override
    public void validate() {

    }
}
