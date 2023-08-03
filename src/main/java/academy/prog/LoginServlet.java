package academy.prog;

import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

// Req -> (S -> S) -> jsp

public class LoginServlet extends HttpServlet {
    static final String LOGIN = "admin";
    static final String PASS = "admin";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String age = request.getParameter("age");

        if (LOGIN.equals(login) && PASS.equals(password) && Integer.parseInt(age) >= 18) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user_login", login);
            response.sendRedirect("index.jsp");
        } else if (Integer.parseInt(age) < 18) {
            PrintWriter pw = response.getWriter();
            pw.println("<html><head><title>Prog.kiev.ua Test</title></head>");
            pw.println("<body><h1>Your age less than 18! </h1></body></html>");
            pw.println("<br>Click this link to <a href=\"/login?a=exit\">logout</a>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String a = request.getParameter("a");
        HttpSession session = request.getSession(false);

        if ("exit".equals(a) && (session != null))
            session.removeAttribute("user_login");

        response.sendRedirect("index.jsp");
    }
}
