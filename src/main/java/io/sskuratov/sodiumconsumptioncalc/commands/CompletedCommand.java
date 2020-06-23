package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CompletedCommand extends AbstractCommand {

    public CompletedCommand(CalcBot calcBot) {
        super(calcBot);
    }

    @Override
    public void execute(Message message, String text) throws TelegramApiException {
        String builder = "Результат вычисления, потребление соли: <b>" +
                text +
                "</b> грамм в сутки." +
                System.lineSeparator() +
                "Суточное потребление поваренной соли превышает допустимый безопасный уровень 3,8 грамм.\n" +
                "<a href=\"https://youtu.be/JRqkYuOXc7Y\"> Посмотрите видео о том как можно улучшить своё здоровье!</a>";
        super.execute(message, builder);
    }
}