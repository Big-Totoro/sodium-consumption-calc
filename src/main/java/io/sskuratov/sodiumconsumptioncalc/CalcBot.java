package io.sskuratov.sodiumconsumptioncalc;

import io.sskuratov.sodiumconsumptioncalc.commands.AnalyzeCommand;
import io.sskuratov.sodiumconsumptioncalc.commands.Command;
import io.sskuratov.sodiumconsumptioncalc.commands.HelpCommand;
import io.sskuratov.sodiumconsumptioncalc.commands.StartCommand;
import io.sskuratov.sodiumconsumptioncalc.dao.User;
import io.sskuratov.sodiumconsumptioncalc.dao.UserRedisDao;
import io.sskuratov.sodiumconsumptioncalc.state.StateMachine;
import io.sskuratov.sodiumconsumptioncalc.state.StateMachinePersist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import redis.clients.jedis.JedisPool;

import java.util.Objects;

public class CalcBot extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(CalcBot.class);

    private final UserService userService = new UserService(
            new UserRedisDao(
                    new JedisPool(Config.REDIS_DB_URL)
            )
    );
    private final StateMachinePersist persist = new StateMachinePersist();

    public CalcBot() {
    }

    @Override
    public void onUpdateReceived(Update update) {
        StateMachine stateMachine = null;

        try {
            if (update.hasMessage()) {
                Message message = update.getMessage();
                if (message != null && message.hasText()) {
                    User user = userService.getUserOrCreateNew(message);
                    logger.info(">>> Thread: " +
                            Thread.currentThread().getId() +
                            ", Id: " +
                            update.getUpdateId() +
                            ", UpdateId: " +
                            user.getUpdateId() +
                            ", Id: " +
                            message.getMessageId() +
                            ", text: " +
                            message.getText());
                    if (update.getUpdateId().compareTo(user.getUpdateId()) == 0) {
                        userService.save(new User(user.getUserId(), user.getUsername(), update.getUpdateId()));
                        return;
                    }

                    stateMachine = persist.restore(user).orElseGet(() -> new StateMachine(this));

                    if ("/старт".equalsIgnoreCase(message.getText()) ||
                            "/start".equalsIgnoreCase(message.getText()) ||
                            "/с".equalsIgnoreCase(message.getText()) ||
                            "/s".equalsIgnoreCase(message.getText())) {
                        stateMachine.reset();
                        Command command = new StartCommand(this);
                        command.execute(message);
                        command = new HelpCommand(this);
                        command.execute(message);
                    } else if ("/помоги".equalsIgnoreCase(message.getText()) ||
                            "/help".equalsIgnoreCase(message.getText()) ||
                            "/п".equalsIgnoreCase(message.getText()) ||
                            "/h".equalsIgnoreCase(message.getText())) {
                        Command command = new HelpCommand(this);
                        command.execute(message);
                    } else if ("/a".equalsIgnoreCase(message.getText()) ||
                            "/а".equalsIgnoreCase(message.getText()) ||
                            "/анализ".equalsIgnoreCase(message.getText())) {
                        Command command = new AnalyzeCommand(this);
                        command.execute(message);
                    } else {
                        stateMachine.handle(message);
                    }

                    persist.save(user, stateMachine);
                    userService.save(new User(user.getUserId(), user.getUsername(), update.getUpdateId()));
                }
            }
        } catch (Exception c) {
            if (stateMachine != null) {
                stateMachine.reset();
                logger.error(Objects.requireNonNull(stateMachine).toString());
            }
            logger.error(c.getMessage());
            c.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "MY BOT NAME HERE!";
    }

    @Override
    public String getBotToken() {
        return "MY BOT TOCKEN HERE!";
    }
}
