package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.dao.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class StateMachinePersist {

    private final Map<Integer, StateMachine> store;

    public StateMachinePersist() {
        store = new ConcurrentHashMap<>();
    }

    /**
     * Restore the state machine for the user
     * @param user The user
     * @return state machine for the user
     */
    public Optional<StateMachine> restore(User user) {
        return Optional.ofNullable(store.get(user.getUserId()));
    }

    /**
     * Save the state machine for the user
     * @param user The user instance
     * @param stateMachine The state machine instance
     */
    public void save(User user, StateMachine stateMachine) {
        store.put(user.getUserId(), stateMachine);
    }
}
