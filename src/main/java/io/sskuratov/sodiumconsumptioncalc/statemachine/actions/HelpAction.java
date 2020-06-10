package io.sskuratov.sodiumconsumptioncalc.statemachine.actions;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.commands.HelpCommand;
import io.sskuratov.sodiumconsumptioncalc.commands.StartCommand;
import io.sskuratov.sodiumconsumptioncalc.statemachine.Events;
import io.sskuratov.sodiumconsumptioncalc.statemachine.States;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelpAction  implements Action<States, Events> {
    @Override
    public void execute(StateContext<States, Events> context) {
        final Message message = context.getExtendedState().get("MESSAGE", Message.class);
        final CalcBot bot = context.getExtendedState().get("BOT", CalcBot.class);
        HelpCommand command = new HelpCommand(bot);
        try {
            command.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
