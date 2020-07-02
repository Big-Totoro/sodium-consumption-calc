package io.sskuratov.sodiumconsumptioncalc.calculations;

import io.sskuratov.sodiumconsumptioncalc.state.States;

import java.util.Map;

public class AbstractFormula implements Formula {

    protected final Map<States, ?> values;
    protected Double creatinineSpotUrineMmol;
    protected Double height;
    protected Double age;
    protected Double weight;
    protected Double sodiumSpotUrine;
    protected Double pottasiumSpotUrine;

    public AbstractFormula(Map<States, ?> values) {
        this.values = values;
    }

    @Override
    public Double evaluate() {
        if (((String)values.get(States.URINE_CREATININE_CONCENTRATION_UNITS)).equalsIgnoreCase("мкмоль/л")) {
            creatinineSpotUrineMmol = ((Double)values.get(States.URINE_CREATININE_CONCENTRATION)) / 1000.0;
        } else if (((String)values.get(States.URINE_CREATININE_CONCENTRATION_UNITS)).equalsIgnoreCase("мг/дл")) {
            creatinineSpotUrineMmol = ((Double)values.get(States.URINE_CREATININE_CONCENTRATION)) / 0.011 / 1000.0;
        } else {
            creatinineSpotUrineMmol = (Double)values.get(States.URINE_CREATININE_CONCENTRATION);
        }
        height = ((Double)values.get(States.HEIGHT)) / 100.0;
        age = (Double)values.get(States.AGE);
        weight = (Double)values.get(States.WEIGHT);
        sodiumSpotUrine = (Double)values.get(States.URINE_SODIUM_CONCENTRATION);
        pottasiumSpotUrine = (Double)values.get(States.URINE_POTASSIUM_CONCENTRATION);

        return 0.0;
    }

    public static Formula get(Map<States, ?> values) {
        if (((String)values.get(States.SEX)).equalsIgnoreCase("МУЖСКОЙ")) {
            return new MaleFormula(values);
        }

        return new FemaleFormula(values);
    }
}
