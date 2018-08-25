package main;

import bot.SimpleBot;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import servlet.Servlet;

import java.net.Authenticator;
import java.net.PasswordAuthentication;


public class Main {
    private static String PROXY_HOST = "45.32.152.77" /* proxy host */;
    private static Integer PROXY_PORT = 23411 /* proxy port */;
    private static String PROXY_USER = "stXSgQ" /* proxy user */;
    private static String PROXY_PASSWORD = "3HLTwL" /* proxy password */;

    public static void main(String[] args) throws Exception {

        // Create the Authenticator that will return auth's parameters for proxy authentication
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(PROXY_USER, PROXY_PASSWORD.toCharArray());
            }
        });
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        // Set up Http proxy
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);

        botOptions.setProxyHost(PROXY_HOST);
        botOptions.setProxyPort(PROXY_PORT);
        // Select proxy type: [HTTP|SOCKS4|SOCKS5] (default: NO_PROXY)
        botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);

        SimpleBot tgBot = new SimpleBot(botOptions);
        try {
            telegramBotsApi.registerBot(tgBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("bot started");
//        tgBot.sendMessageWithLocation("test", 146601835L);




        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new Servlet()), "/servletBot");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(80);
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
