package slbedu.library.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import slbedu.library.dao.BookDAO;

import com.google.gson.Gson;

@WebServlet(urlPatterns = "/BookServlet")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1523071258964697936L;
    
    @EJB
    private BookDAO bookDAO;

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");

        Gson gson = new Gson();
        String resource = null;

        if (bookId == null) {
            resource = gson.toJson(bookDAO.getAllBooks());
        } else {
            resource = gson.toJson(bookDAO.findById(Long.parseLong(bookId)));
        }

        resp.setContentType("application/json");
        resp.getWriter().print(resource);
        resp.getWriter().flush();
    }


}
