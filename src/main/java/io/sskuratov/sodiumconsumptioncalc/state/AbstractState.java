package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.commands.Command;
import io.sskuratov.sodiumconsumptioncalc.commands.ErrorCommand;
import io.sskuratov.sodiumconsumptioncalc.constraints.Constraint;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class AbstractState<T> implements State<T> {

    protected final Constraint<T> constraint;

    public AbstractState(Constraint<T> constraint) {
        this.constraint = constraint;
    }

    @Override
    public void error(CalcBot bot, Message message, String error) throws TelegramApiException {
        Command command = new ErrorCommand(bot);
        command.execute(message);
    }
}
