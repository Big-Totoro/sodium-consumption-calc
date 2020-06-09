package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.commands.*;
import io.sskuratov.sodiumconsumptioncalc.constraints.CreatinineConcentration;
import io.sskuratov.sodiumconsumptioncalc.constraints.CreatinineConcentrationUnits;
import io.sskuratov.sodiumconsumptioncalc.constraints.PotassiumConcentration;
import io.sskuratov.sodiumconsumptioncalc.constraints.SodiumConcentration;
import io.sskuratov.sodiumconsumptioncalc.dao.User;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;

public class CalcStateMachine {

    private final User user;
    private final CalcStateMachinePersist persist;
    private final CalcBot calcBot;

    public CalcStateMachine(User user, CalcStateMachinePersist persist, CalcBot calcBot) {
        this.persist = persist;
        this.user = user;
        this.calcBot = calcBot;
    }

    public void reset() {
        persist.reset(user);
    }

    public State<?> getLastState() {
        return persist.getLastState(user);
    }

    public Command perform(String text) {
        State<?> state = persist.getLastState(user);
        State<?> nextState;

        try {
            Command command = null;

            switch(state.getId()) {
                case INIT :
                    command = new InitCommand(calcBot);
                    nextState = new UrineCreatinineConcentration(new CreatinineConcentration());
                case URINE_CREATININE_CONCENTRATION :
                    command = new UrineCreatinineConcentrationCommand(calcBot);
                    nextState = new UrineCreatinineConcentrationUnits(new CreatinineConcentrationUnits());
                    break;
                case URINE_CREATININE_CONCENTRATION_UNITS :
                    command = new CreatinineConcentrationUnitsCommand(calcBot);
                    nextState = new UrineSodiumConcentration(new SodiumConcentration());
                    break;
                case URINE_SODIUM_CONCENTRATION :
                    command = new UrineSodiumConcentrationCommand(calcBot);
                    nextState = new UrinePotassiumConcentration(new PotassiumConcentration());
                    break;
                case URINE_POTASSIUM_CONCENTRATION :
                    command = new UrinePotassiumConcentrationCommand(calcBot);
                    nextState = new Sex(new io.sskuratov.sodiumconsumptioncalc.constraints.Sex());
                    break;
                case SEX :
                    command = new SexCommand(calcBot);
                    nextState = new Age(new io.sskuratov.sodiumconsumptioncalc.constraints.Age());
                    break;
                case AGE :
                    command = new AgeCommand(calcBot);
                    nextState= new Height(new io.sskuratov.sodiumconsumptioncalc.constraints.Height());
                    break;
                case HEIGHT :
                    command = new HeightCommand(calcBot);
                    nextState = new Weight(new io.sskuratov.sodiumconsumptioncalc.constraints.Weight());
                    break;
                case WEIGHT :
                    command = new WeightCommand(calcBot, persist.getStates(user));
                    nextState = new InitState();
                    break;
                default :
                    command = new InitCommand(calcBot);
                    nextState = new InitState();
                    break;
            }

            state.parseValue(text);
            state.validate();
            persist.appendState(user, nextState);

            return command;
        } catch (InputException e) {

            return new ErrorCommand(calcBot, e.getMessage());

        } catch (NumberFormatException e) {

            return new ErrorCommand(calcBot, "Некорректная строка \"" + text + "\"");

        } catch (Exception e) {

            return new ErrorCommand(calcBot, e.getMessage());
        }
    }
}
