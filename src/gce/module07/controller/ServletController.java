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

import gce.module07.model.Item;
import gce.module07.model.ItemDao;
import gce.module07.model.ItemDaoImpl;

@WebServlet("/")
public class ServletController extends HttpServlet {
    private static final long serialVersionUID = 127517L;
    private ItemDao itemDao;

    @Override
    public void init() {
        itemDao = new ItemDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    newItemForm(request, response);
                    break;
                case "/insert":
                    insertItem(request, response);
                    break;
                case "/edit":
                    editItemForm(request, response);
                    break;
                case "/update":
                    updateItem(request, response);
                    break;
                case "/delete":
                    deleteItem(request, response);
                    break;
                default:
                    listItem(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void newItemForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("new.jsp");

        dispatcher.forward(request, response);
    }

    private void insertItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Item newItem = processFormData(request);;

        itemDao.insertItem(newItem);

        response.sendRedirect("list");
    }

    private static Item processFormData(HttpServletRequest request) {
        String itemDescription = request.getParameter("itemDescription");
        String itemDetails = request.getParameter("itemDetails");
        String itemDueDate = request.getParameter("itemDueDate");

        Item newItem = new Item();
        newItem.setItemDescription(itemDescription);
        newItem.setItemDetails(itemDetails);
        newItem.setItemDueDate(LocalDate.parse(itemDueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        return newItem;
    }

    private void editItemForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Item selectedItem = itemDao.selectItem(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");

        request.setAttribute("item", selectedItem);

        dispatcher.forward(request, response);
    }

    private void updateItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        Item updatedItem = processFormData(request);

        updatedItem.setId(Integer.parseInt(request.getParameter("id")));

        itemDao.updateItem(updatedItem);

        response.sendRedirect("list");
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        itemDao.deleteItemById(id);

        response.sendRedirect("list");
    }

    private void listItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Item> listItem = itemDao.loadAllItems();

        request.setAttribute("listItem", listItem);

        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");

        dispatcher.forward(request, response);
    }
}
