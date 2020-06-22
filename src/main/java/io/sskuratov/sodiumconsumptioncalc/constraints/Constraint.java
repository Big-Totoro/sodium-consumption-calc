package io.sskuratov.sodiumconsumptioncalc.constraints;

import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;

public interface Constraint<T> {

    /**
     * Validates the value against the availability range
     * @param value The value to validate
     * @throws InputException Incorrect input exception
     */
    void validate(T value) throws InputException;
}