package io.sskuratov.sodiumconsumptioncalc;

import io.sskuratov.sodiumconsumptioncalc.calculations.FemaleFormula;
import io.sskuratov.sodiumconsumptioncalc.calculations.Formula;
import io.sskuratov.sodiumconsumptioncalc.calculations.MaleFormula;
import io.sskuratov.sodiumconsumptioncalc.state.States;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculationsTests {

    private Map<States, Double> toValues(
            Double creatinineConcentration,
            Double sodiumConcentration,
            Double potassiumConcentration,
            Double age,
            Double height,
            Double weight
            ) {
        Map<States, Double> values = new HashMap<>();
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
                        3456.0,
                        130.0,
                        25.0,
                        80.0,
                        178.0,
                        105.0
                )
        );
        assertThat(
                BigDecimal.valueOf(formula.evaluate()).round(new MathContext(3))
        ).isEqualTo(
                new BigDecimal("6.60")
        );
    }

    @Test
    public void femaleZeroCalculation() {
        Formula formula = new FemaleFormula(
                toValues(
                        0.0,
                        0.0,
                        0.0,
                        0.0,
                        0.0,
                        0.0
                )
        );
        assertThat(formula.evaluate().isNaN()).isTrue();
    }

    @Test
    public void maleRegularCalculation() {
        Formula formula = new MaleFormula(
                toValues(
                        3456.0,
                        130.0,
                        25.0,
                        80.0,
                        178.0,
                        105.0
                )
        );
        assertThat(
                BigDecimal.valueOf(formula.evaluate()).round(new MathContext(4))
        ).isEqualTo(
                BigDecimal.valueOf(13.18)
        );
    }

    @Test
    public void maleZeroCalculation() {
        Formula formula = new MaleFormula(
                toValues(
                        0.0,
                        0.0,
                        0.0,
                        0.0,
                        0.0,
                        0.0
                )
        );
        assertThat(formula.evaluate().isNaN()).isTrue();
    }
}
