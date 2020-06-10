package io.sskuratov.sodiumconsumptioncalc;

import io.sskuratov.sodiumconsumptioncalc.commands.Command;
import io.sskuratov.sodiumconsumptioncalc.commands.DefaultCommand;
import io.sskuratov.sodiumconsumptioncalc.commands.InitCommand;
import io.sskuratov.sodiumconsumptioncalc.dao.User;
import io.sskuratov.sodiumconsumptioncalc.statemachine.Events;
import io.sskuratov.sodiumconsumptioncalc.statemachine.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

@Component
public class CalcBot extends TelegramLongPollingBot {

    private final UserService userService;
    private final StateMachineFactory<States, Events> stateMachineFactory;
    private StateMachinePersister<States, Events, Integer> persister;

    @Autowired
    public CalcBot(
            UserService userService,
            StateMachineFactory<States, Events> stateMachineFactory,
            StateMachinePersister<States, Events, Integer> persister) {
        this.userService = userService;
        this.stateMachineFactory = stateMachineFactory;
        this.persister = persister;
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
        final StateMachine<States, Events> stateMachine;

        try {
            if (update.hasMessage()) {

                Message message = update.getMessage();
                if (message != null && message.hasText()) {
                    User user = userService.getUserOrCreateNew(message);
                    Command command = new DefaultCommand(this);
                    stateMachine = stateMachineFactory.getStateMachine();
                    persister.restore(stateMachine, user.getUserId());

                    stateMachine.getExtendedState().getVariables().put("MESSAGE", message);
                    stateMachine.getExtendedState().getVariables().put("BOT", this);

                    if ("/старт".equalsIgnoreCase(message.getText()) ||
                        "/start".equalsIgnoreCase(message.getText()) ||
                        "/с".equalsIgnoreCase(message.getText()) ||
                        "/s".equalsIgnoreCase(message.getText())) {
                        stateMachine.sendEvent(Events.START);
                    } else if ("/помоги".equalsIgnoreCase(message.getText()) ||
                               "/help".equalsIgnoreCase(message.getText()) ||
                               "/п".equalsIgnoreCase(message.getText()) ||
                               "/h".equalsIgnoreCase(message.getText())) {
                        stateMachine.sendEvent(Events.HELP);
                    } else if ("/1".equalsIgnoreCase(message.getText())) {
                        //stateMachine.get().reset();
                        //command = new InitCommand(this);
                        stateMachine.sendEvent(Events.URINE_CREATININE_CONCENTRATION);

                    } else {
                        //command = stateMachine.get().perform(message.getText());
                    }

                    persister.persist(stateMachine, user.getUserId());

                    command.execute(message);
                }
            }
        } catch (Exception c) {
            //stateMachine.ifPresent(m -> m.reset());
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
