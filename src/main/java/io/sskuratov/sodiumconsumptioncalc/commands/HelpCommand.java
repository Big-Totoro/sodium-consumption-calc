package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelpCommand extends AbstractCommand {

    public HelpCommand(CalcBot calcBot) {
        super(calcBot);
    }

    @Override
    public void execute(Message message) throws TelegramApiException {
        super.execute(message,
                "Для управления Telegram-ботом можно использовать команды." +
                        System.lineSeparator() +
                        "Каждая команда должна начинаться с символа косой черты: \"/\". " +
                        "Для одной команды может быть несколько вариантов, в основном для ускорения записи." +
                        System.lineSeparator() +
                        "1) <b>/start</b>, <b>/s</b>, <b>/с</b>, <b>/старт</b> - показать стартовое сообщение" +
                        System.lineSeparator() +
                        "2) <b>/help</b>, <b>/h</b>, <b>/помоги</b>, <b>/п</b> - показать справочное сообщение" +
                        System.lineSeparator() +
                        "3) <b>/a</b>, <b>/а</b>, <b>/анализ</b> - показать инструкцию о том, как подготовить анализ для " +
                        "определения суточного потребления соли."

        );
    }
}
