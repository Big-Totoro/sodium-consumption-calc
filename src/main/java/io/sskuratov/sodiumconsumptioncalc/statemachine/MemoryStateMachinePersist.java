package io.sskuratov.sodiumconsumptioncalc.statemachine;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.util.HashMap;

public class MemoryStateMachinePersist implements StateMachinePersist<States, Events, Integer> {

    private final HashMap<Integer, StateMachineContext<States, Events>> contexts = new HashMap<>();

    @Override
    public void write(StateMachineContext<States, Events> context, Integer userId) throws Exception {
        contexts.put(userId, context);
    }

    @Override
    public StateMachineContext<States, Events> read(Integer userId) throws Exception {
        return contexts.get(userId);
    }
}
