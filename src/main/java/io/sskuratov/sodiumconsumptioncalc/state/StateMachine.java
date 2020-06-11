package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.dao.User;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StateMachine {

    private Logger logger = LoggerFactory.getLogger(StateMachine.class);

    private State<?> state = new InitState();
    private Map<States, State<?>> states = new ConcurrentHashMap<>();

    private final User user;
    private final StateMachinePersist persist;
    private final CalcBot bot;

    public StateMachine(User user, StateMachinePersist persist, CalcBot bot) {
        this.persist = persist;
        this.user = user;
        this.bot = bot;
    }

    void setState(State<?> state) {
        this.state = state;
    }

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
                state.execute(bot, message);
            } else {
                state.execute(bot, message);
            }
        } catch (InputException e) {
            state.error(bot, message, "");
        } catch (TelegramApiException e) {
            logger.debug(">>> handle: " + e.getMessage());
        }

        logger.debug("<<< handle: ");
    }

    public void reset() {
        state = new InitState();
        states.clear();
    }
}
