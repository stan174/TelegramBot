package bot;

import model.Volunteer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendLocation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.VolunteerService;
import service.VolunteerServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class SimpleBot extends TelegramLongPollingBot {

//    private static  final VolunteerService volunter = new VolunteerServiceImpl();


    public SimpleBot(DefaultBotOptions botOptions) {
        super(botOptions);
    }


    public SimpleBot() {
        super();
    }

//    public void sendMessageWithLocation(long id, long chatId) {
////
////        SendMessage message = new SendMessage() // Create a message object object
////                .setChatId(chatId)
////                .setText(msg);
////        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
////        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
////        List<InlineKeyboardButton> rowInline = new ArrayList<>();
////        rowInline.add(new InlineKeyboardButton().setText("Я помогу БаБулЕ!").setCallbackData("update_msg_text"));
////        // Set the keyboard to the markup
////        rowsInline.add(rowInline);
////        // Add it to the message
////        markupInline.setKeyboard(rowsInline);
////        message.setReplyMarkup(markupInline);
////        try {
////            // - этот код отправляет геопозицию
////            Float lat = 59.9418720f, lng = 30.2655820f;
////            SendLocation sendLocation = new SendLocation(lat, lng);
////            sendLocation.setChatId(chatId);
////            // - конец кода
////
////            execute(sendLocation);
////            execute(message); // Call method to send the message
////        } catch (TelegramApiException e) {
////            e.printStackTrace();
////        }
////    }
////
    public void sendMessage(String msg, long chatId) {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId)
                .setText(msg);
        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
//            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            String messageFromUser = update.getMessage().getText();

            if (messageFromUser.equals("/start")) {
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chatId)
                        .setText("Добро пожаловать в приложение  <Помоги Пенсионеру>  Зарегистрируйтесь, если вы зашли первый раз");
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText("Регистрация").setCallbackData("registration"));
                rowInline.add(new InlineKeyboardButton().setText("Онлайн").setCallbackData("update_msg_text2"));


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
//                    Float lat = 59.9418720f, lng = 30.2655820f;
//                    SendLocation sendLocation = new SendLocation(lat, lng);
//                    sendLocation.setChatId(chatId);
//                    // - конец кода
//
//                    execute(sendLocation);
                    execute(message); // Call method to send the message
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            long chatId = callbackQuery.getFrom().getId();
            String button = callbackQuery.getData();
            if (button.equals("registration") ) {
                    sendMessage("Введите вашу Фамилию Имя возвраст, через пробел", chatId);
                    update.
//               String s1 =  update.hasMessage();
                   // String[] name = str.split(" ");
              //  Volunteer vol = new Volunteer(name[0],name[1],name[2],true,"0");
             //   VolunteerService volService = new VolunteerServiceImpl();
               // volService.addVolonter(vol);
             //   sendMessage("Вы успешно зарегистрированы! Если вы готовы начать работу, нажмите кнопку ONLINE," +
            //            "если хотите закончить работу нажмите OFFLINE", chatId);

            }
        }
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