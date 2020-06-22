package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.commands.Command;
import io.sskuratov.sodiumconsumptioncalc.commands.UrineSodiumConcentrationCommand;
import io.sskuratov.sodiumconsumptioncalc.constraints.Constraint;
import io.sskuratov.sodiumconsumptioncalc.constraints.PotassiumConcentration;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.math.BigDecimal;

public class UrineSodiumConcentration extends AbstractState<BigDecimal> {

    private BigDecimal value;

    public UrineSodiumConcentration(Constraint<BigDecimal> constraint) {
        super(constraint);
    }

    @Override
    public States getId() {
        return States.URINE_SODIUM_CONCENTRATION;
    }

    @Override
    public BigDecimal get() {
        return value;
    }

    @Override
    public void parseValue(String value) {
        this.value = new BigDecimal(value.trim().replace(",", "."));
    }

    @Override
    public void next(StateMachine stateMachine) {
        stateMachine.setState(new UrinePotassiumConcentration(new PotassiumConcentration()));
    }

    @Override
    public void validate() throws InputException {
        constraint.validate(value);
    }

    @Override
    public void execute(CalcBot bot, Message message) throws TelegramApiException {
        Command command = new UrineSodiumConcentrationCommand(bot);
        command.execute(message);
    }

    @Override
    public String toString() {
        return "id:" + getId().name() + ", value: " + value;
    }
}
