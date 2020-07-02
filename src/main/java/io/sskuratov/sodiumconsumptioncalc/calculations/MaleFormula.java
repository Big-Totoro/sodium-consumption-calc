package io.sskuratov.sodiumconsumptioncalc.calculations;

import io.sskuratov.sodiumconsumptioncalc.state.States;

import java.util.Map;

public class MaleFormula extends AbstractFormula {

    public MaleFormula(Map<States, ?> values) {
        super(values);
    }

    @Override
    public Double evaluate() {
        super.evaluate();

        Double sodium_24_INTERSALT;

        /*
         1.       23 * ( (25.46 + 0.46*$sodium_spot_urine) -
         2.              (2.75 * $creatinine_spot_urine_mmol) -
         3.              (0.13 * $pottasium_spot_urine) +
         4.              4.10 * $weight / pow($height_meter, 2) +
         5.              (0.26 * $age) );
         */
        Double expr1 = 25.46 + 0.46 * sodiumSpotUrine;
        Double expr2 = 2.75 * creatinineSpotUrineMmol;
        Double expr3 = 0.13 * pottasiumSpotUrine;
        Double expr4 = 4.10 * weight / Math.pow(height, 2);
        Double expr5 = 0.26 * age;

        sodium_24_INTERSALT = 23L * (expr1 - expr2 - expr3 + expr4 + expr5);

        Double sodium_24_INTERSALT_g = sodium_24_INTERSALT/ 1000.0;

        return 2.5 * sodium_24_INTERSALT_g;
    }
}
