package servlet;

import Bot.SimpleBot;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Servlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)  {
       String ids = request.getParameter("address");
//        Long id = Long.parseLong(ids);
        SimpleBot bot = new SimpleBot();
//        bot.sendMessage(ids,id);

    }

//    public void doPost()


}
