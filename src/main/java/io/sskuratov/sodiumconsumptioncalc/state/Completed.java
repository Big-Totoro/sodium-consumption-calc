package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.calculations.FemaleFormula;
import io.sskuratov.sodiumconsumptioncalc.calculations.Formula;
import io.sskuratov.sodiumconsumptioncalc.calculations.MaleFormula;
import io.sskuratov.sodiumconsumptioncalc.commands.AbstractCommand;
import io.sskuratov.sodiumconsumptioncalc.commands.CompletedCommand;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Completed extends AbstractState<String> {

    private Map<States, State<?>> states;

    public Completed() {
        super(null);
    }

    @Override
    public States getId() {
        return States.COMPLETED;
    }

    @Override
    public String get() {
        return "";
    }

    @Override
    public void set(String value) {

    }

    @Override
    public void parseValue(String value) {

    }

    @Override
    public void next(StateMachine stateMachine) {
        stateMachine.setState(new InitState());
    }

    @Override
    public void validate() {

    }

    @Override
    public void execute(CalcBot bot, Message message) throws TelegramApiException {
        AbstractCommand command = new CompletedCommand(bot);
        command.execute(message, calculate(states).toString());
    }

    public void setStates(Map<States, State<?>> states) {
        Objects.requireNonNull(states);

        this.states = states;
    }

    public BigDecimal calculate(Map<States, State<?>> states) {
        Map<States, ?> values = states.entrySet().stream()
                .filter(v -> v.getKey() != States.INIT)
                .filter(v -> v.getKey() != States.COMPLETED)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        v -> v.getValue().get())
                );
        Formula formula;

        if (((String)values.get(States.SEX)).equalsIgnoreCase("МУЖСКОЙ")) {
            formula = new MaleFormula(values);
        } else {
            formula = new FemaleFormula(values);
        }

        return formula.evaluate();
    }

    @Override
    public String toString() {
        return "id:" + getId().name() + ", value: -";
    }
}
