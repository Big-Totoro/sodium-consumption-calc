package io.sskuratov.sodiumconsumptioncalc.commands;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Command {

    /**
     * Sends the reply to Telegram user
     * @param message The message we reply on
     * @throws TelegramApiException Telegram exception
     */
    void execute(Message message) throws TelegramApiException;
}
