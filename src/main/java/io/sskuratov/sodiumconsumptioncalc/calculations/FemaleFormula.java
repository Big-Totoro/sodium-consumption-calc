package io.sskuratov.sodiumconsumptioncalc.calculations;

import io.sskuratov.sodiumconsumptioncalc.state.States;

import java.util.Map;

public class FemaleFormula extends AbstractFormula {

    public FemaleFormula(Map<States, ?> values) {
        super(values);
    }

    @Override
    public Double evaluate() {
        super.evaluate();

        Double sodium_24_INTERSALT;

        /*
         1.       23 * ( (5.07 + 0.34 * $sodium_spot_urine) -
         2.                (2.16 * $creatinine_spot_urine_mmol) -
         3.                (0.09 * $pottasium_spot_urine) +
         4.                2.39 * $weight/pow($height_meter, 2) +
         5.                (2.35 * $age) -
         6.                0.03 * pow($age, 2) )
         */
        Double expr1 = 5.07 + 0.34 * sodiumSpotUrine;
        Double expr2 = 2.16 * creatinineSpotUrineMmol;
        Double expr3 = 0.09 * pottasiumSpotUrine;
        Double expr4 = 2.39 * weight / Math.pow(height, 2.0);
        Double expr5 = 2.35 * age;
        Double expr6 = 0.03 * Math.pow(age, 2.0);
        sodium_24_INTERSALT = 23.0 * (expr1 - expr2 - expr3 + expr4 + expr5 - expr6);

        Double sodium_24_INTERSALT_g = sodium_24_INTERSALT / 1000.0;

        return 2.5 * sodium_24_INTERSALT_g;
    }
}
