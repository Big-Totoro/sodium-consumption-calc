package io.sskuratov.sodiumconsumptioncalc;

import io.sskuratov.sodiumconsumptioncalc.calculations.FemaleFormula;
import io.sskuratov.sodiumconsumptioncalc.calculations.Formula;
import io.sskuratov.sodiumconsumptioncalc.calculations.MaleFormula;
import io.sskuratov.sodiumconsumptioncalc.state.States;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CalculationsTests {

    private Map<States, BigDecimal> toValues(
            BigDecimal creatinineConcentration,
            BigDecimal sodiumConcentration,
            BigDecimal potassiumConcentration,
            BigDecimal age,
            BigDecimal height,
            BigDecimal weight
            ) {
        Map<States, BigDecimal> values = new HashMap<>();
        values.put(States.URINE_CREATININE_CONCENTRATION, creatinineConcentration);
        values.put(States.URINE_SODIUM_CONCENTRATION, sodiumConcentration);
        values.put(States.URINE_POTASSIUM_CONCENTRATION, potassiumConcentration);
        values.put(States.AGE, age);
        values.put(States.HEIGHT, height);
        values.put(States.WEIGHT, weight);

        return values;
    }

    @Test
    public void femaleRegularCalculation() {
        Formula formula = new FemaleFormula(
                toValues(
                        BigDecimal.valueOf(0.003),
                        BigDecimal.valueOf(0.03),
                        BigDecimal.valueOf(0.03),
                        BigDecimal.valueOf(38.0),
                        BigDecimal.valueOf(168.0),
                        BigDecimal.valueOf(90.0)
                )
        );
        assertThat(formula.evaluate()).isEqualTo(BigDecimal.valueOf(7.2));
    }

    @Test
    public void femaleZeroCalculation() {
        Formula formula = new FemaleFormula(
                toValues(
                        BigDecimal.valueOf(0L),
                        BigDecimal.valueOf(0L),
                        BigDecimal.valueOf(0L),
                        BigDecimal.valueOf(0L),
                        BigDecimal.valueOf(0L),
                        BigDecimal.valueOf(0L)
                )
        );
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(formula::evaluate);
    }

    @Test
    public void maleRegularCalculation() {
        Formula formula = new MaleFormula(
                toValues(
                        BigDecimal.valueOf(0.003),
                        BigDecimal.valueOf(0.03),
                        BigDecimal.valueOf(0.03),
                        BigDecimal.valueOf(38.0),
                        BigDecimal.valueOf(168.0),
                        BigDecimal.valueOf(90.0)
                )
        );
        assertThat(formula.evaluate()).isEqualTo(BigDecimal.valueOf(9.4));
    }

    @Test
    public void maleZeroCalculation() {
        Formula formula = new MaleFormula(
                toValues(
                        BigDecimal.valueOf(0L),
                        BigDecimal.valueOf(0L),
                        BigDecimal.valueOf(0L),
                        BigDecimal.valueOf(0L),
                        BigDecimal.valueOf(0L),
                        BigDecimal.valueOf(0L)
                )
        );
        assertThatExceptionOfType(ArithmeticException.class).isThrownBy(formula::evaluate);
    }
}
