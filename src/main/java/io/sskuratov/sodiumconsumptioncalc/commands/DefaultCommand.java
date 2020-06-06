package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class DefaultCommand implements Command {

    private final CalcBot calcBot;

    public DefaultCommand(CalcBot calcBot) {
        this.calcBot = calcBot;
    }

    @Override
    public void execute(Message message) {

    }
}
