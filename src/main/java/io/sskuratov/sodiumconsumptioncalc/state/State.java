package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;

import java.util.Optional;

public interface State<T> {
    States getId();

    Optional<T> get();
    void set(T value);

    void parseValue(String value) throws InputException;
    States next();
    void validate() throws InputException;
}
