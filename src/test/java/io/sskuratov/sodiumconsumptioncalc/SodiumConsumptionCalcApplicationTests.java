package io.sskuratov.sodiumconsumptioncalc;

import io.sskuratov.sodiumconsumptioncalc.statemachine.Events;
import io.sskuratov.sodiumconsumptioncalc.statemachine.States;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

@SpringBootTest
class SodiumConsumptionCalcApplicationTests {

    @Autowired
    private StateMachineFactory<States, Events> factory;

    @Test
    void initState() {
        StateMachine<States, Events> stateMachine = factory.getStateMachine();

    }

}
