package io.sskuratov.sodiumconsumptioncalc.calculations;

import io.sskuratov.sodiumconsumptioncalc.state.States;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Map;

public class MaleFormula implements Formula {

    private final Map<States, ?> values;

    public MaleFormula(Map<States, ?> values) {
        this.values = values;
    }

    @Override
    public BigDecimal evaluate() {
        BigDecimal creatinineSpotUrineMmol = ((BigDecimal)values.get(States.URINE_CREATININE_CONCENTRATION))
                .divide(BigDecimal.valueOf(1000L), RoundingMode.HALF_DOWN);
        BigDecimal height = ((BigDecimal)values.get(States.HEIGHT))
                .divide(BigDecimal.valueOf(100L), RoundingMode.HALF_DOWN);
        BigDecimal age = (BigDecimal)values.get(States.AGE);
        BigDecimal weight = (BigDecimal)values.get(States.WEIGHT);
        BigDecimal sodiumSpotUrine = (BigDecimal)values.get(States.URINE_SODIUM_CONCENTRATION);
        BigDecimal pottasiumSpotUrine = (BigDecimal)values.get(States.URINE_SODIUM_CONCENTRATION);
        BigDecimal sodium_24_INTERSALT;

        /*
         1.       23 * ( (25.46 + 0.46*$sodium_spot_urine) -
         2.              (2.75 * $creatinine_spot_urine_mmol) -
         3.              (0.13 * $pottasium_spot_urine) +
         4.              4.10 * $weight / pow($height_meter, 2) +
         5.              (0.26*$age) );
         */
        BigDecimal expr1 = BigDecimal.valueOf(25.46).add(BigDecimal.valueOf(0.46).multiply(sodiumSpotUrine));
        BigDecimal expr2 = BigDecimal.valueOf(2.75).multiply(creatinineSpotUrineMmol);
        BigDecimal expr3 = BigDecimal.valueOf(0.13).multiply(pottasiumSpotUrine);
        BigDecimal expr4 = BigDecimal.valueOf(4.10).multiply(weight).divide(height.pow(2), RoundingMode.HALF_DOWN);
        BigDecimal expr5 = BigDecimal.valueOf(0.26).multiply(age);

        sodium_24_INTERSALT = BigDecimal.valueOf(23L).multiply(
                expr1.subtract(expr2).subtract(expr3).add(expr4).add(expr5)
        );

        BigDecimal sodium_24_INTERSALT_g = sodium_24_INTERSALT.divide(BigDecimal.valueOf(1000L), RoundingMode.HALF_DOWN);
        BigDecimal salt_24_INTERSALT_g = BigDecimal.valueOf(2.5).multiply(sodium_24_INTERSALT_g).round(new MathContext(2));

        return salt_24_INTERSALT_g;
    }
}
