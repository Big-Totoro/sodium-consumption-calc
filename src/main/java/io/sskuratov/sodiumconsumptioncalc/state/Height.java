package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.commands.Command;
import io.sskuratov.sodiumconsumptioncalc.commands.HeightCommand;
import io.sskuratov.sodiumconsumptioncalc.constraints.Constraint;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Height extends AbstractState<Double> {

    private Double value;

    public Height(Constraint<Double> constraint) {
        super(constraint);
    }

    @Override
    public States getId() {
        return States.HEIGHT;
    }

    @Override
    public Double get() {
        return value;
    }

    @Override
    public void parseValue(String value) {
        this.value = Double.valueOf(value.trim().replace(",", "."));
    }

    @Override
    public void next(StateMachine stateMachine) {
        stateMachine.setState(new Weight(new io.sskuratov.sodiumconsumptioncalc.constraints.Weight()));
    }

    @Override
    public void validate() throws InputException {
        constraint.validate(value);
    }

    @Override
    public void execute(CalcBot bot, Message message) throws TelegramApiException {
        Command command = new HeightCommand(bot);
        command.execute(message);
    }
    @Override
    public String toString() {
        return "id:" + getId().name() + ", value: " + value;
    }
}