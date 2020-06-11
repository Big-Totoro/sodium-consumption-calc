package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.dao.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StateMachinePersist {

    private final Map<Integer, StateMachine> store;

    public StateMachinePersist() {
        store = new ConcurrentHashMap<>();
    }

    public Optional<StateMachine> restore(User user) {
        return Optional.ofNullable(store.get(user.getUserId()));
    }

    public void save(User user, StateMachine stateMachine) {
        store.put(user.getUserId(), stateMachine);
    }
}
