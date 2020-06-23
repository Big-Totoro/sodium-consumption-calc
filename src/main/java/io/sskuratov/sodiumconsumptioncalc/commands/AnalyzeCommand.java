package io.sskuratov.sodiumconsumptioncalc.commands;

import io.sskuratov.sodiumconsumptioncalc.CalcBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AnalyzeCommand extends AbstractCommand {
    /**
     * Constructor
     *
     * @param calcBot Telegram bot
     */
    public AnalyzeCommand(CalcBot calcBot) {
        super(calcBot);
    }

    @Override
    public void execute(Message message) throws TelegramApiException {
        String text =
                "Сдают разовую утреннюю мочу с определением в ней содержания натрия, " +
                "креатинина, калия и последующим расчётом\n" +
                "по результатам исследования суточного потребления поваренной соли. " +
                "Эти вопросы подробно рассмотрены в следующем\n" +
                "<a href=\"https://youtu.be/vvfmmjtQrVI\"> видео</a>";
        super.execute(message, text);
    }
}
