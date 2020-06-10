package io.sskuratov.sodiumconsumptioncalc.statemachine;

import io.sskuratov.sodiumconsumptioncalc.state.InitState;
import io.sskuratov.sodiumconsumptioncalc.statemachine.actions.HelpAction;
import io.sskuratov.sodiumconsumptioncalc.statemachine.actions.StartAction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates()
                .initial(States.INIT)
                .end(States.COMPLETED)
                .states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config) throws Exception {
        config.withConfiguration()
                .autoStartup(true)
                .listener(new StateMachineListener());
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal()
                .source(States.INIT).target(States.INIT).event(Events.START)
                .action(startAction())
                .and()

                .withExternal()
                .source(States.INIT).target(States.INIT).event(Events.HELP)
                .action(helpAction())
                .and()

                .withExternal()
                .source(States.INIT).target(States.URINE_CREATININE_CONCENTRATION).event(Events.URINE_CREATININE_CONCENTRATION)

                .and()
                .withExternal()
                .source(States.URINE_CREATININE_CONCENTRATION).target(States.URINE_CREATININE_CONCENTRATION_UNITS).event(Events.URINE_CREATININE_CONCENTRATION_UNITS)
                .and()

                .withExternal()
                .source(States.URINE_CREATININE_CONCENTRATION_UNITS).target(States.URINE_SODIUM_CONCENTRATION).event(Events.URINE_SODIUM_CONCENTRATION)
                .and()

                .withExternal()
                .source(States.URINE_SODIUM_CONCENTRATION).target(States.URINE_POTASSIUM_CONCENTRATION).event(Events.E4)
                .and()

                .withExternal()
                .source(States.URINE_POTASSIUM_CONCENTRATION).target(States.SEX).event(Events.E5)
                .and()

                .withExternal()
                .source(States.SEX).target(States.AGE).event(Events.E6)
                .and()

                .withExternal()
                .source(States.AGE).target(States.HEIGHT).event(Events.E7)
                .and()

                .withExternal()
                .source(States.HEIGHT).target(States.WEIGHT).event(Events.E8)
                .and()

                .withExternal()
                .source(States.WEIGHT).target(States.COMPLETED).event(Events.E9);
    }

    @Bean
    public StateMachinePersister<States, Events, Integer> persister() {
        return new DefaultStateMachinePersister<>(new MemoryStateMachinePersist());
    }

    @Bean
    public Action<States, Events> startAction() {
        return new StartAction();
    }

    @Bean
    public Action<States, Events> helpAction() {
        return new HelpAction();
    }
}
