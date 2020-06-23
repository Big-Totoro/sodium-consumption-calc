package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand extends AbstractCommand {

    public StartCommand(CalcBot calcBot) {
        super(calcBot);
    }

    @Override
    public void execute(Message message) throws TelegramApiException {
        super.execute(message,
                "Telegram-бот \"Калькулятор потребления соли\" " +
                        "поможет Вам вычислить суточное потребление поваренной соли. " +
                        System.lineSeparator() +
                        "Калькулятор поможет понять - нужно ли Вам ограничить потребление соли и даст рекомендации." +
                        System.lineSeparator() +
                        System.lineSeparator() +
                        "Чтобы вычислить суточное потребление соли необходимы следующие параметры, " +
                        "которые можно получить сдав анализ: " +
                        System.lineSeparator() +
                        "1. Концентрация креатинина в разовой порции мочи (мкмоль/л, ммоль/л или мн/дл)" +
                        System.lineSeparator() +
                        "2. Концентрация натрия в разовой порции мочи (ммоль/л)" +
                        System.lineSeparator() +
                        "3. Концентрация калия в разовой порции мочи (ммоль/л)" +
                        System.lineSeparator() +
                        "4. Пол (М/Ж)" +
                        System.lineSeparator() +
                        "5. Возраст (полных лет)" +
                        System.lineSeparator() +
                        "6. Рост (см)" +
                        System.lineSeparator() +
                        "7. Вес (кг)" +
                        System.lineSeparator() +
                        System.lineSeparator() +
                        "Для начала работы отправьте боту какое-нибудь сообщение. " +
                        "Это может быть произвольный текст, хештэг или имя пациента, которое поможет вам в дальнейшем найти " +
                        "эти расчёты через поиск Telegram."
                );
    }
}
