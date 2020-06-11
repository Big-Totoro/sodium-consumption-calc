package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class AbstractCommand implements Command {

    protected final CalcBot calcBot;

    protected AbstractCommand(CalcBot calcBot) {
        this.calcBot = calcBot;
    }

    @Override
    public void execute(Message message) throws TelegramApiException {
    }

    public void execute(Message message, String text) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        calcBot.execute(sendMessage);
    }

    @Override
    public void error(Message message) throws TelegramApiException {
    }

    public void error(Message message, String error) throws TelegramApiException {
        execute(message, error);
    }
}
