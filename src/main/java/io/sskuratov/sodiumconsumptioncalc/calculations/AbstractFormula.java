package io.sskuratov.sodiumconsumptioncalc.calculations;

import io.sskuratov.sodiumconsumptioncalc.state.States;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class AbstractFormula implements Formula {

    protected final Map<States, ?> values;
    protected BigDecimal creatinineSpotUrineMmol;
    protected BigDecimal height;
    protected BigDecimal age;
    protected BigDecimal weight;
    protected BigDecimal sodiumSpotUrine;
    protected BigDecimal pottasiumSpotUrine;

    public AbstractFormula(Map<States, ?> values) {
        this.values = values;
    }

    @Override
    public BigDecimal evaluate() {
        creatinineSpotUrineMmol = ((BigDecimal)values.get(States.URINE_CREATININE_CONCENTRATION))
                .divide(BigDecimal.valueOf(1000L), RoundingMode.HALF_DOWN);
        height = ((BigDecimal)values.get(States.HEIGHT))
                .divide(BigDecimal.valueOf(100L), RoundingMode.HALF_DOWN);
        age = (BigDecimal)values.get(States.AGE);
        weight = (BigDecimal)values.get(States.WEIGHT);
        sodiumSpotUrine = (BigDecimal)values.get(States.URINE_SODIUM_CONCENTRATION);
        pottasiumSpotUrine = (BigDecimal)values.get(States.URINE_SODIUM_CONCENTRATION);

        return BigDecimal.ZERO;
    }
}
