package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

public interface State<T> {
    States getId();

    Optional<T> get();
    void set(T value);

    void parseValue(String value) throws InputException;

    void next(StateMachine stateMachine);
    void validate() throws InputException;

    void execute(CalcBot bot, Message message) throws TelegramApiException;

    void error(CalcBot bot, Message message, String error) throws TelegramApiException;
}
