package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.state.CalcState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class UrineSodiumConcentrationCommand implements Command {

    private final CalcBot calcBot;

    public UrineSodiumConcentrationCommand(CalcBot calcBot) {
        this.calcBot = calcBot;
    }

    @Override
    public void execute(Message message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(
                "Шаг 3 из 7: Далее, введите параметр: \"" +
                        CalcState.URINE_POTASSIUM_CONCENTRATION.getEntity().getCaption() +
                        "\"");

        calcBot.execute(sendMessage);
    }
}
