package io.sskuratov.sodiumconsumptioncalc.constraints;

import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;

public interface Constraint<T> {

    /**
     * Returns the maximum available value
     * @return Maximum value
     */
    T getMax();

    /**
     * Returns the minumum available value
     * @return Minimum value
     */
    T getMin();

    /**
     * Validates the value against the availability range
     * @param value The value to validate
     * @throws InputException Incorrect input exception
     */
    void validate(T value) throws InputException;
}