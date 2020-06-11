package io.sskuratov.sodiumconsumptioncalc;

import io.sskuratov.sodiumconsumptioncalc.dao.User;
import io.sskuratov.sodiumconsumptioncalc.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserOrCreateNew(Message message) {
        return userRepository.findUserByUserId(message.getFrom().getId())
                .orElseGet(() -> new User(
                                "",
                                message.getFrom().getId(),
                                message.getFrom().getUserName(),
                                message.getFrom().getFirstName(),
                                message.getFrom().getLastName()
                        )
                );
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
