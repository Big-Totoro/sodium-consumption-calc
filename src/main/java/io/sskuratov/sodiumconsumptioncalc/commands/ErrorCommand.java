package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ErrorCommand implements Command {

    private final CalcBot calcBot;
    private final String error;

    public ErrorCommand(CalcBot calcBot, String error) {
        this.calcBot = calcBot;
        this.error = error;
    }

    @Override
    public void execute(Message message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(error);

        calcBot.execute(sendMessage);
    }
}
