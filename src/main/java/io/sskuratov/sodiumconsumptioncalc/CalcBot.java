package io.sskuratov.sodiumconsumptioncalc;

import io.sskuratov.sodiumconsumptioncalc.commands.*;
import io.sskuratov.sodiumconsumptioncalc.dao.User;
import io.sskuratov.sodiumconsumptioncalc.state.CalcStateMachine;
import io.sskuratov.sodiumconsumptioncalc.state.CalcStateMachinePersist;
import io.sskuratov.sodiumconsumptioncalc.state.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class CalcBot extends TelegramLongPollingBot {

    private final UserService userService;
    private final CalcStateMachinePersist persist = new CalcStateMachinePersist();

    @Autowired
    public CalcBot(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void registerBot(){
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        Optional<CalcStateMachine> stateMachine = Optional.empty();

        try {
            if (update.hasMessage()) {

                Message message = update.getMessage();
                if (message != null && message.hasText()) {
                    User user = userService.getUserOrCreateNew(message);
                    Command command = new DefaultCommand(this);
                    stateMachine = Optional.of(new CalcStateMachine(user, persist, this));

                    if ("/старт".equalsIgnoreCase(message.getText()) ||
                        "/start".equalsIgnoreCase(message.getText()) ||
                        "/с".equalsIgnoreCase(message.getText()) ||
                        "/s".equalsIgnoreCase(message.getText())) {
                        command = new StartCommand(this);
                        stateMachine.get().reset();
                    } else if ("/помоги".equalsIgnoreCase(message.getText()) ||
                               "/help".equalsIgnoreCase(message.getText()) ||
                               "/п".equalsIgnoreCase(message.getText()) ||
                               "/h".equalsIgnoreCase(message.getText())) {
                        command = new HelpCommand(this);
                    } else if ("/1".equalsIgnoreCase(message.getText())) {
                        stateMachine.get().reset();
                        command = new InitCommand(this);
                    } else {
                        command = stateMachine.get().perform(message.getText());
                    }

                    command.execute(message);
                    userService.save(user);
                    if (stateMachine.get().getLastState().getId() == States.INIT) {
                        stateMachine.get().reset();
                    }
                }
            }
        } catch (Exception c) {
            stateMachine.ifPresent(m -> m.reset());
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

    @ExceptionHandler(Exception.class)
    public void handler() {

    }
}
