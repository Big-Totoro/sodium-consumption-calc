package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.commands.Command;
import io.sskuratov.sodiumconsumptioncalc.commands.UrinePotassiumConcentrationCommand;
import io.sskuratov.sodiumconsumptioncalc.constraints.Constraint;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.math.BigDecimal;
import java.util.Optional;

public class UrinePotassiumConcentration extends AbstractState<BigDecimal> {

    private BigDecimal value;

    public UrinePotassiumConcentration(Constraint<BigDecimal> constraint) {
        super(constraint);
    }

    @Override
    public States getId() {
        return States.URINE_POTASSIUM_CONCENTRATION;
    }

    @Override
    public Optional<BigDecimal> get() {
        return Optional.ofNullable(value);
    }

    @Override
    public void set(BigDecimal units) {
        this.value = units;
    }

    @Override
    public void next(StateMachine stateMachine) {
        stateMachine.setState(new Sex(new io.sskuratov.sodiumconsumptioncalc.constraints.Sex()));
    }

    @Override
    public void parseValue(String value) {
        this.value = new BigDecimal(value.trim().replace(",", "."));
    }

    @Override
    public void validate() throws InputException {
        constraint.validate(value);
    }

    @Override
    public void execute(CalcBot bot, Message message) throws TelegramApiException {
        Command command = new UrinePotassiumConcentrationCommand(bot);
        command.execute(message);
    }

    @Override
    public String toString() {
        return "id:" + getId().name() + ", value: " + value;
    }
}
