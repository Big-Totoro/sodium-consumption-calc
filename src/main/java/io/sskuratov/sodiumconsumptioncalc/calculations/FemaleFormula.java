package io.sskuratov.sodiumconsumptioncalc.calculations;

import io.sskuratov.sodiumconsumptioncalc.state.States;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Map;

public class FemaleFormula extends AbstractFormula {

    public FemaleFormula(Map<States, ?> values) {
        super(values);
    }

    @Override
    public BigDecimal evaluate() {
        super.evaluate();

        BigDecimal sodium_24_INTERSALT;

        /*
         1.       23 * ( (5.07 + 0.34 * $sodium_spot_urine) -
         2.                (2.16 * $creatinine_spot_urine_mmol) -
         3.                (0.09 * $pottasium_spot_urine) +
         4.                2.39 * $weight/pow($height_meter, 2) +
         5.                (2.35 * $age) -
         6.                0.03 * pow($age, 2) )
         */
        BigDecimal expr1 = BigDecimal.valueOf(5.07).add(BigDecimal.valueOf(0.34).multiply(sodiumSpotUrine));
        BigDecimal expr2 = BigDecimal.valueOf(2.16).multiply(creatinineSpotUrineMmol);
        BigDecimal expr3 = BigDecimal.valueOf(0.09).multiply(pottasiumSpotUrine);
        BigDecimal expr4 = BigDecimal.valueOf(2.39).multiply(weight).divide(height.pow(2), RoundingMode.HALF_DOWN);
        BigDecimal expr5 = BigDecimal.valueOf(2.35).multiply(age);
        BigDecimal expr6 = BigDecimal.valueOf(0.03).multiply(age.pow(2));
        sodium_24_INTERSALT = BigDecimal.valueOf(23L).multiply(
                expr1.subtract(expr2).subtract(expr3).add(expr4).add(expr5).subtract(expr6)
        );

        BigDecimal sodium_24_INTERSALT_g = sodium_24_INTERSALT.divide(BigDecimal.valueOf(1000L), RoundingMode.HALF_DOWN);

        return BigDecimal.valueOf(2.5).multiply(sodium_24_INTERSALT_g).round(new MathContext(2));
    }
}
