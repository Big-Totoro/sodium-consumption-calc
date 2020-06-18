package io.sskuratov.sodiumconsumptioncalc;

import io.sskuratov.sodiumconsumptioncalc.commands.AnalyzeCommand;
import io.sskuratov.sodiumconsumptioncalc.commands.Command;
import io.sskuratov.sodiumconsumptioncalc.commands.HelpCommand;
import io.sskuratov.sodiumconsumptioncalc.commands.StartCommand;
import io.sskuratov.sodiumconsumptioncalc.dao.User;
import io.sskuratov.sodiumconsumptioncalc.state.StateMachine;
import io.sskuratov.sodiumconsumptioncalc.state.StateMachinePersist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class CalcBot extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(CalcBot.class);

    private final UserService userService;
    private final StateMachinePersist persist = new StateMachinePersist();

    @Autowired
    public CalcBot(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void registerBot() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        StateMachine stateMachine = null;

        try {
            if (update.hasMessage()) {

                Message message = update.getMessage();
                if (message != null && message.hasText()) {
                    User user = userService.getUserOrCreateNew(message);

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
                    userService.save(user);
                }
            }
        } catch (Exception c) {
            if (stateMachine != null) {
                stateMachine.reset();
                logger.error(Objects.requireNonNull(stateMachine).toString());
            }
            logger.error(c.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return "salt_consumption_calc_bot";
    }

    @Override
    public String getBotToken() {
        return "1003185955:AAF8T3FIrcLkWS2dupQpD0dgJuAsnRjJAXI";
    }
}
