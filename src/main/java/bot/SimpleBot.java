package Bot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class SimpleBot extends TelegramLongPollingBot {
    long chatId;

    public SimpleBot(DefaultBotOptions botOptions) {
        super(botOptions);
    }

    public SimpleBot() {
        super();
    }

    public void sendMessage(String msg, Long chatId){
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId)
                .setText(msg);
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Я помогу БаБулЕ!").setCallbackData("update_msg_text"));
        // Set the keyboard to the markup
        rowsInline.add(rowInline);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        try {
            // - этот код отправляет геопозицию
            Float lat = 59.9418720f, lng = 30.2655820f;
            SendLocation sendLocation = new SendLocation(lat, lng);
            sendLocation.setChatId(chatId);
            // - конец кода

            execute(sendLocation);
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }

    @Override
	public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            this.chatId =chatId;
            if (update.getMessage().getText().equals("/start")) {


                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chatId)
                        .setText("Срочно нужна помощь человеку!");
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Я помогу БаБулЕ!").setCallbackData("update_msg_text"));
                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
//                try {
//                    execute(message); // Sending our message object to user
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }

                try {
                    // - этот код отправляет геопозицию
                    Float lat = 59.9418720f, lng = 30.2655820f;
                    SendLocation sendLocation = new SendLocation(lat, lng);
                    sendLocation.setChatId(chatId);
                    // - конец кода

                    execute(sendLocation);
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {

            }
        } else if (update.hasCallbackQuery()) {}
	}

	@Override
	public String getBotUsername() {
		return "Pensooo_bot";
	}

	@Override
	public String getBotToken() {
		return "692766715:AAGtc_qrrSs-0YIH4H8cnvX_Fl8WXA6jK8A";
	}

}