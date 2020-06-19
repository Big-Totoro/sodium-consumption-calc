package io.sskuratov.sodiumconsumptioncalc;

import io.sskuratov.sodiumconsumptioncalc.dao.User;
import io.sskuratov.sodiumconsumptioncalc.dao.UserDao;
import org.telegram.telegrambots.meta.api.objects.Message;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserOrCreateNew(Message message) {
        return userDao.findUserByUserId(message.getFrom().getId())
                .orElseGet(() -> new User(
                                message.getFrom().getId(),
                                message.getFrom().getUserName(),
                                message.getFrom().getFirstName(),
                                message.getFrom().getLastName(),
                                -1
                        )
                );
    }

    public void save(User user) {
        userDao.save(user);
    }
}
