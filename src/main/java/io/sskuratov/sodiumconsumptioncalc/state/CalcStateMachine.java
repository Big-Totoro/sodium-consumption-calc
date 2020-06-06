package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.commands.*;
import io.sskuratov.sodiumconsumptioncalc.dao.User;
import io.sskuratov.sodiumconsumptioncalc.exceptions.IncorrectSexException;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;

import java.math.BigDecimal;

public class CalcStateMachine {

    private User user;
    private CalcStateMachinePersist persist;
    private CalcBot calcBot;

    public CalcStateMachine(User user, CalcStateMachinePersist persist, CalcBot calcBot) {
        this.persist = persist;
        this.user = user;
        this.calcBot = calcBot;
    }

    public void reset() {
        persist.reset(user);
    }

    public CalcState getLastState() {
        return persist.getLastState(user);
    }

    public Command perform(String text) {
        CalcState state = persist.getLastState(user);
        state = state.nextState();

        try {
            Command command = null;
            BigDecimal value = new BigDecimal(text.replace(",", "."));
            state.validate(value);
            state.setStateValue(value);
            persist.appendState(user, state);

            switch(state) {
                case URINE_CREATININE_CONCENTRATION :
                    command = new UrineCreatinineConcentrationCommand(calcBot);
                    break;
                case URINE_SODIUM_CONCENTRATION :
                    command = new UrineSodiumConcentrationCommand(calcBot);
                    break;
                case URINE_POTASSIUM_CONCENTRATION :
                    command = new UrinePotassiumConcentrationCommand(calcBot);
                    break;
                case SEX :
                    command = new SexCommand(calcBot);
                    break;
                case AGE :
                    command = new AgeCommand(calcBot);
                    break;
                case HEIGHT :
                    command = new HeightCommand(calcBot);
                    break;
                case WEIGHT :
                    command = new WeightCommand(calcBot, persist.getStates(user));
                    persist.appendState(user, CalcState.INIT);
                    break;
            }

            return command;
        } catch (NumberFormatException e) {
            StringBuilder builder = new StringBuilder();
            builder.append("На данный момент бот ожидает ввода значения для ");
            builder.append("\"");
            builder.append(state.getEntity().getCaption());
            builder.append("\". ");
            builder.append(System.lineSeparator());

            builder.append("Допустимые значения для данного параметра находятся в интервале от ");
            builder.append(state.getEntity().getMin());
            builder.append(" до ");
            builder.append(state.getEntity().getMax());
            builder.append(System.lineSeparator());

            builder.append("Возможно, Вы допустили ошибку при вводе числа: \"");
            builder.append(text);
            builder.append("\". Пожалуйста, используйте точку для разделения целой и дробной части, например, 0.01");
            return new ErrorCommand(calcBot,
                    builder.toString());
        } catch (IncorrectSexException e) {
            StringBuilder builder = new StringBuilder();
            builder.append("Допустимые значения для данного параметра - 0 для Мужского пола и 1 для Женского пола.");
            builder.append(System.lineSeparator());
            return new ErrorCommand(calcBot,
                    builder.toString());
        } catch (InputException e) {
            StringBuilder builder = new StringBuilder();
            builder.append("Допустимые значения для данного параметра находятся в интервале от ");
            builder.append(state.getEntity().getMin());
            builder.append(" до ");
            builder.append(state.getEntity().getMax());
            builder.append(System.lineSeparator());
            return new ErrorCommand(calcBot,
                    builder.toString());
        }
    }
}
