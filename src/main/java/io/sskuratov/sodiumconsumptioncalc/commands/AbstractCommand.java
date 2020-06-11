package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class AbstractCommand implements Command {

    protected final CalcBot calcBot;

    /**
     * Constructor
     * @param calcBot Telegram bot
     */
    protected AbstractCommand(CalcBot calcBot) {
        this.calcBot = calcBot;
    }

    @Override
    public void execute(Message message) throws TelegramApiException {
    }

    /**
     * Send the reply to Telegram user
     * @param message The message we reply on
     * @param text The text we send to Telegram user
     * @throws TelegramApiException Telegram exception
     */
    public void execute(Message message, String text) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        calcBot.execute(sendMessage);
    }
}
