package io.sskuratov.sodiumconsumptioncalc.statemachine.actions;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.commands.StartCommand;
import io.sskuratov.sodiumconsumptioncalc.statemachine.Events;
import io.sskuratov.sodiumconsumptioncalc.statemachine.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartAction implements Action<States, Events> {

    @Override
    public void execute(StateContext<States, Events> context) {
        final Message message = context.getExtendedState().get("MESSAGE", Message.class);
        final CalcBot bot = context.getExtendedState().get("BOT", CalcBot.class);
        StartCommand command = new StartCommand(bot);
        try {
            command.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
