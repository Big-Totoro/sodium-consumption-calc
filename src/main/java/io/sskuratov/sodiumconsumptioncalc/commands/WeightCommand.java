package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.state.State;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.math.BigDecimal;
import java.util.List;

public class WeightCommand implements Command {

    private final CalcBot calcBot;
    private final List<State<?>> states;

    public WeightCommand(CalcBot calcBot, List<State<?>> states) {
        this.calcBot = calcBot;
        this.states = states;
    }

    @Override
    public void execute(Message message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(
                "Результат вычисления: \"" +
                        calculate() +
                        "\"");

        calcBot.execute(sendMessage);
    }

    private BigDecimal calculate() {
        /*
        Map<State<?>, State<?>> values = states.stream()
                .filter(v -> v.getId() != States.INIT)
                .filter(v -> v.getId() != States.COMPLETED)
                .collect(Collectors.toMap(k -> k, v -> v.get().get()));
        Formula formula;

        if (values.get(States.SEX).compareTo(BigDecimal.ZERO) == 0) {
            formula = new MaleFormula(values);
        } else {
            formula = new FemaleFormula(values);
        }

        return formula.evaluate();
         */
        return null;
    }
}
