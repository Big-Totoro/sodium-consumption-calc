package io.sskuratov.sodiumconsumptioncalc.state;

import io.sskuratov.sodiumconsumptioncalc.dao.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CalcStateMachinePersist {

    private final Map<Integer, List<CalcState>> store;

    public CalcStateMachinePersist() {
        store = new HashMap<>();
    }

    public void reset(User user) {
        store.computeIfPresent(user.getUserId(), (k, v) -> {
            v.clear();
            v.add(CalcState.INIT);
            return v;
        });
    }

    public CalcState getLastState(User user) {
        store.computeIfAbsent(user.getUserId(), (k) -> {
            List<CalcState> state = new ArrayList<>();
            state.add(CalcState.INIT);
            return state;
        });
        List<CalcState> states = store.get(user.getUserId());

        return states.get(states.size() - 1);
    }

    public void appendState(User user, CalcState state) {
        store.get(user.getUserId()).add(state);
    }

    public List<CalcState> getStates(User user) {
        return store.get(user.getUserId());
    }
}
