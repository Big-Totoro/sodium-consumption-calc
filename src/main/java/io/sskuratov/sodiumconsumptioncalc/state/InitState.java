package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.commands.Command;
import io.sskuratov.sodiumconsumptioncalc.commands.InitCommand;
import io.sskuratov.sodiumconsumptioncalc.constraints.CreatinineConcentration;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class InitState extends AbstractState<String> {

    public InitState() {
        super(null);
    }

    @Override
    public States getId() {
        return States.INIT;
    }

    @Override
    public String get() {
        return "";
    }

    @Override
    public void parseValue(String value) {

    }

    @Override
    public void next(StateMachine stateMachine) {
        stateMachine.setState(new UrineCreatinineConcentration(new CreatinineConcentration()));
    }

    @Override
    public void validate() {
    }

    @Override
    public void execute(CalcBot bot, Message message) throws TelegramApiException {
        Command command = new InitCommand(bot);
        command.execute(message);
    }
    @Override
    public String toString() {
        return "id:" + getId().name() + ", value: -";
    }
}
