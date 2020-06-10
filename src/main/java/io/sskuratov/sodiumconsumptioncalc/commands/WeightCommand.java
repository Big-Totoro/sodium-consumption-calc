package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.calculations.FemaleFormula;
import io.sskuratov.sodiumconsumptioncalc.calculations.Formula;
import io.sskuratov.sodiumconsumptioncalc.calculations.MaleFormula;
import io.sskuratov.sodiumconsumptioncalc.state.CalcState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WeightCommand implements Command {

    private final CalcBot calcBot;
    private final List<CalcState> states;

    public WeightCommand(CalcBot calcBot, List<CalcState> states) {
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
        Map<CalcState, BigDecimal> values = states.stream()
                .filter(v -> v != CalcState.INIT)
                .filter(v -> v != CalcState.COMPLETED)
                .collect(Collectors.toMap(k -> k, v -> v.getStateValue()));
        Formula formula;

        if (values.get(CalcState.SEX).compareTo(BigDecimal.ZERO) == 0) {
            formula = new MaleFormula(values);
        } else {
            formula = new FemaleFormula(values);
        }

        return formula.evaluate();
    }
}
