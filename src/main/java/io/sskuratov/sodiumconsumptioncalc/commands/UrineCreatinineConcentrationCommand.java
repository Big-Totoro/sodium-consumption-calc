package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class UrineCreatinineConcentrationCommand extends AbstractCommand {

    public UrineCreatinineConcentrationCommand(CalcBot calcBot) {
        super(calcBot);
    }

    @Override
    public void execute(Message message) throws TelegramApiException {
        super.execute(message,
                "Шаг 1 из 7: Введите параметр: \"" +
                        "Концентрация креатинина в разовой порции мочи" +
                        "\"");
    }
}
