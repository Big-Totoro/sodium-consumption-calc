package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface State<T> {
    /**
     * Returns the state based on enum
     * @return the state based on enum
     */
    States getId();

    /**
     * Returns the value related to the state
     * @return the value
     */
    T get();

    /**
     * Sets the value for the state
     * @param value The value to store
     */
    void set(T value);

    /**
     * Parses the value
     * @param value to parse
     * @throws InputException Incorrect input exception
     */
    void parseValue(String value) throws InputException;

    /**
     * Returns the next state related to the workflow
     * @param stateMachine The state machine
     */
    void next(StateMachine stateMachine);

    /**
     * Validates the value against the state available range
     * @throws InputException Incorrect input exception
     */
    void validate() throws InputException;

    /**
     * Sends the message to Telegram user
     * @param bot Telegram bot
     * @param message Incoming massage we reply on
     * @throws TelegramApiException Telegram exception
     */
    void execute(CalcBot bot, Message message) throws TelegramApiException;

    /**
     * Sends the message with error description
     * @param bot Telegram bot
     * @param message Incoming massage we reply on
     * @param error description to the user
     * @throws TelegramApiException Telegram exception
     */
    void error(CalcBot bot, Message message, String error) throws TelegramApiException;
}
