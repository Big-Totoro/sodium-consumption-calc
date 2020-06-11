package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

public class Error extends AbstractState<String> {

    private String error;

    public Error() {
        super(null);
    }

    @Override
    public States getId() {
        return States.ERROR;
    }

    @Override
    public Optional<String> get() {
        return Optional.empty();
    }

    @Override
    public void set(String error) {
        this.error = error;
    }

    @Override
    public void parseValue(String value) {

    }

    @Override
    public void next(StateMachine stateMachine) {

    }

    @Override
    public void validate() {

    }

    @Override
    public void execute(CalcBot bot, Message message) throws TelegramApiException {

    }

    @Override
    public String toString() {
        return "id:" + getId().name() + ", value: " + error;
    }
}
