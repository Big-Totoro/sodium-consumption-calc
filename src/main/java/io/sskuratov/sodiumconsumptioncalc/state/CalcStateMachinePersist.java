package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.dao.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CalcStateMachinePersist {

    private final Map<Integer, List<State<?>>> store;

    public CalcStateMachinePersist() {
        store = new ConcurrentHashMap<>();
    }

    public void reset(User user) {
        store.computeIfPresent(user.getUserId(), (k, v) -> {
            v.clear();
            v.add(new InitState());
            return v;
        });
    }

    public State<?> getLastState(User user) {
        store.computeIfAbsent(user.getUserId(), (k) -> {
            List<State<?>> state = new CopyOnWriteArrayList<>();
            state.add(new InitState());
            return state;
        });
        List<State<?>> states = store.get(user.getUserId());

        return states.get(states.size() - 1);
    }

    public void appendState(User user, State<?> state) {
        store.get(user.getUserId()).add(state);
    }

    public List<State<?>> getStates(User user) {
        return store.get(user.getUserId());
    }
}
