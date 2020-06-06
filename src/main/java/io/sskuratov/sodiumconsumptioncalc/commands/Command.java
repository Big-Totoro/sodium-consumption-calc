package io.sskuratov.sodiumconsumptioncalc.commands;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface Command {

    void execute(Message message) throws TelegramApiException;
}
