package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class InitCommand extends AbstractCommand {

    public InitCommand(CalcBot calcBot) {
        super(calcBot);
    }

    @Override
    public void execute(Message message) throws TelegramApiException {
        super.execute(message, "Введите сообщение, например, имя пациента для старта калькулятора");
    }
}
