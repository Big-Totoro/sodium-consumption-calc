package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StateMachine {

    private final Logger logger = LoggerFactory.getLogger(StateMachine.class);

    private State<?> state = new InitState();
    private final Map<States, State<?>> states = new ConcurrentHashMap<>();
    private final CalcBot bot;

    /**
     * Constructor
     * @param bot Telegram bot
     */
    public StateMachine(CalcBot bot) {
        this.bot = bot;
    }

    /**
     * Save the state to the state machine
     * @param state The state to store
     */
    void setState(State<?> state) {
        this.state = state;
    }

    /**
     * Handle the incoming message from Telegram user
     * @param message Telegram message
     * @throws TelegramApiException Telegram exception
     */
    public void handle(Message message) throws TelegramApiException {
        logger.debug(">>> handle: " + message.getText());

        try {
            logger.debug(">>> handle:" + state.toString());

            state.parseValue(message.getText());
            state.validate();
            states.put(state.getId(), state);
            state.next(this);

            if (state.getId() == States.COMPLETED) {
                ((Completed)state).setStates(states);
                state.execute(bot, message);
                reset();
            }
            state.execute(bot, message);
        } catch (InputException e) {
            state.error(bot, message, e.getMessage());
        } catch (TelegramApiException e) {
            logger.debug(">>> handle: " + e.getMessage());
        } catch (NumberFormatException e) {
            state.error(bot, message,
                    "Вы неверно ввели значение для \"" +
                    state.getId().getDescription() +
                    "\"");
        }

        logger.debug("<<< handle: ");
    }

    /**
     * Reset the state machine to the initial state. Clear all previous states.
     */
    public void reset() {
        state = new InitState();
        states.clear();
    }
}
