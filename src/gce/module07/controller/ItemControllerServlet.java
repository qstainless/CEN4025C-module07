package gce.module07.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gce.module07.model.Data;
import gce.module07.model.Item;

@WebServlet("/")
public class ItemControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertItem(request, response);
                    break;
                case "/delete":
                    deleteItem(request, response);
                    break;
                default:
                    listItems(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listItems(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Item> listItems = Data.getInstance().getItems();

        request.setAttribute("listItems", listItems);

        RequestDispatcher dispatcher = request.getRequestDispatcher("item-list.jsp");

        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("item-form.jsp");

        dispatcher.forward(request, response);
    }

    private void insertItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String itemDescription = request.getParameter("itemDescription");
        String itemDetails = request.getParameter("itemDetails");
        String itemDueDate = request.getParameter("itemDueDate");

        Item newItem = new Item();
        newItem.setItemDescription(itemDescription);
        newItem.setItemDetails(itemDetails);
        newItem.setItemDueDate(LocalDate.parse(itemDueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        response.sendRedirect("list");
    }


    private void deleteItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

//        Data.getInstance().deleteItem(id);

        response.sendRedirect("list");
    }
}
