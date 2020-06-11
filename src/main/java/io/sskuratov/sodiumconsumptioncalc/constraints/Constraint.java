package io.sskuratov.sodiumconsumptioncalc.constraints;

import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;

public interface Constraint<T> {
    T getMax();
    T getMin();
    void validate(T value) throws InputException;
}