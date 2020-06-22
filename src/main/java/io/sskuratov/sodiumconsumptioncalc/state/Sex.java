package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.commands.Command;
import io.sskuratov.sodiumconsumptioncalc.commands.SexCommand;
import io.sskuratov.sodiumconsumptioncalc.constraints.Constraint;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Sex extends AbstractState<String> {

    private String sex;

    public Sex(Constraint<String> constraint) {
        super(constraint);
    }

    @Override
    public States getId() {
        return States.SEX;
    }

    @Override
    public String get() {
        return sex;
    }

    @Override
    public void parseValue(String value) {
        this.sex = value;
    }

    @Override
    public void next(StateMachine stateMachine) {
        stateMachine.setState(new Age(new io.sskuratov.sodiumconsumptioncalc.constraints.Age()));
    }

    @Override
    public void validate() throws InputException {
        constraint.validate(sex);
    }

    @Override
    public void execute(CalcBot bot, Message message) throws TelegramApiException {
        Command command = new SexCommand(bot);
        command.execute(message);
    }

    @Override
    public String toString() {
        return "id:" + getId().name() + ", value: " + sex;
    }
}
