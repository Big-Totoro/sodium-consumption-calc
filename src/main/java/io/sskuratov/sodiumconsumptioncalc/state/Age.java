package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.commands.AgeCommand;
import io.sskuratov.sodiumconsumptioncalc.commands.Command;
import io.sskuratov.sodiumconsumptioncalc.constraints.Constraint;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.math.BigDecimal;

public class Age extends AbstractState<BigDecimal> {

    private BigDecimal value;

    public Age(Constraint<BigDecimal> constraint) {
        super(constraint);
    }

    @Override
    public States getId() {
        return States.AGE;
    }

    @Override
    public BigDecimal get() {
        return value;
    }

    @Override
    public void set(BigDecimal value) {
        this.value = value;
    }

    @Override
    public void parseValue(String value) {
        this.value = new BigDecimal(value.trim().replace(",", "."));
    }

    @Override
    public void next(StateMachine stateMachine) {
        stateMachine.setState(new Height(new io.sskuratov.sodiumconsumptioncalc.constraints.Height())); //States.HEIGHT;
    }

    @Override
    public void validate() throws InputException {
        constraint.validate(value);
    }

    @Override
    public void execute(CalcBot bot, Message message) throws TelegramApiException {
        Command command = new AgeCommand(bot);
        command.execute(message);
    }

    @Override
    public String toString() {
        return "id:" + getId().name() + ", value: " + value;
    }
}