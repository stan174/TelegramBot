package servlet;

import bot.SimpleBot;
import dao.PensionerDAOImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Servlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)  {
       Long id = Long.parseLong(request.getParameter("pensionerId"));
//        SimpleBot bot = new SimpleBot();
        System.out.println(id);
//        PensionerDAOImpl pens = new PensionerDAOImpl();
//        String address = pens.getAddress(id);
//        System.out.println(address);
//        bot.sendMessage(address,1L);


    }

//    public void doPost()


}
