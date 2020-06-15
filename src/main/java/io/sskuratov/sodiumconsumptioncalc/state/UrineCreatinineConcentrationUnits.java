package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import io.sskuratov.sodiumconsumptioncalc.commands.Command;
import io.sskuratov.sodiumconsumptioncalc.commands.CreatinineConcentrationUnitsCommand;
import io.sskuratov.sodiumconsumptioncalc.constraints.Constraint;
import io.sskuratov.sodiumconsumptioncalc.constraints.SodiumConcentration;
import io.sskuratov.sodiumconsumptioncalc.exceptions.InputException;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class UrineCreatinineConcentrationUnits extends AbstractState<String> {

    private String units;

    public UrineCreatinineConcentrationUnits(Constraint<String> constraint) {
        super(constraint);
    }

    @Override
    public States getId() {
        return States.URINE_CREATININE_CONCENTRATION_UNITS;
    }

    @Override
    public String get() {
        return units;
    }

    @Override
    public void set(String units) {
        this.units = units;
    }

    @Override
    public void parseValue(String value) {
        this.units = value;
    }

    @Override
    public void next(StateMachine stateMachine) {
        stateMachine.setState(new UrineSodiumConcentration(new SodiumConcentration()));
    }

    @Override
    public void validate() throws InputException {
        constraint.validate(units);
    }

    @Override
    public void execute(CalcBot bot, Message message) throws TelegramApiException {
        Command command = new CreatinineConcentrationUnitsCommand(bot);
        command.execute(message);
    }

    @Override
    public String toString() {
        return "id:" + getId().name() + ", value: " + units;
    }
}
