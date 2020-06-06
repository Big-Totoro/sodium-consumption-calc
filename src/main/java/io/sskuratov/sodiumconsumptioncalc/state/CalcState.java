package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.entities.*;
import io.sskuratov.sodiumconsumptioncalc.exceptions.IncorrectSexException;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;

import java.math.BigDecimal;

public enum CalcState {
    INIT(new Init()) {
        @Override
        public CalcState nextState() {
            return URINE_CREATININE_CONCENTRATION;
        }
    },

    URINE_CREATININE_CONCENTRATION(new CreatinineConcentration(BigDecimal.ZERO)) {
        @Override
        public CalcState nextState() {
            return URINE_SODIUM_CONCENTRATION;
        }
    },

    URINE_SODIUM_CONCENTRATION(new SodiumConcentration(BigDecimal.ZERO)) {
        @Override
        public CalcState nextState() {
            return URINE_POTASSIUM_CONCENTRATION;
        }
    },

    URINE_POTASSIUM_CONCENTRATION(new PotassiumConcentration(BigDecimal.ZERO)) {
        @Override
        public CalcState nextState() {
            return SEX;
        }
    },

    SEX(new Sex(Sex.Units.MALE)) {
        @Override
        public CalcState nextState() {
            return AGE;
        }
    },

    AGE(new Age(BigDecimal.ZERO)) {
        @Override
        public CalcState nextState() {
            return HEIGHT;
        }
    },

    HEIGHT(new Height(BigDecimal.ZERO)) {
        @Override
        public CalcState nextState() {
            return WEIGHT;
        }
    },

    WEIGHT(new Weight(BigDecimal.ZERO)) {
        @Override
        public CalcState nextState() {
            return INIT;
        }
    },

    COMPLETED(new Completed()) {
        @Override
        public CalcState nextState() {
            return INIT;
        }
    };

    private Entity entity;
    private BigDecimal stateValue;

    CalcState(Entity entity) {
        this.entity = entity;
    }

    public abstract CalcState nextState();

    public void setStateValue(BigDecimal value) {
        this.stateValue = value;
    }

    public BigDecimal getStateValue() {
        return stateValue;
    }

    public Entity getEntity() {
        return entity;
    }

    public void validate(BigDecimal value) throws InputException {
        if ((value.compareTo(entity.getMax()) > 0) ||
                (value.compareTo(entity.getMin()) < 0)) {
            if (this == SEX) {
                throw new IncorrectSexException();
            } else {
                throw new InputException();
            }
        }
    }
}