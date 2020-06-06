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
    private static final long serialVersionUID = 1L;
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

        System.out.println(action);

        try {
            switch (action) {
                case "/new":
                    newItemForm(request, response);
                    break;
                case "/insert.jsp":
                    insertItem(request, response);
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
        System.out.println("Clicked on new");
        RequestDispatcher dispatcher = request.getRequestDispatcher("new.jsp");

        dispatcher.forward(request, response);
    }

    private void insertItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String itemDescription = request.getParameter("itemDescription");
        String itemDetails = request.getParameter("itemDetails");
        String itemDueDate = request.getParameter("itemDueDate");

        System.out.println(itemDescription + "\n" + itemDetails + "\n" + itemDueDate);

        Item newItem = new Item();
        newItem.setItemDescription(itemDescription);
        newItem.setItemDetails(itemDetails);
        newItem.setItemDueDate(LocalDate.parse(itemDueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        itemDao.insertItem(newItem);

        response.sendRedirect("list");
    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);

        itemDao.deleteItemById(id);

        response.sendRedirect("list");
    }

    private void listItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("Im listItem");
        List<Item> listItem = itemDao.loadAllItems();

        request.setAttribute("listItem", listItem);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");

        dispatcher.forward(request, response);
    }
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String itemDescription = request.getParameter("itemDescription");
//        String itemDetails = request.getParameter("itemDetails");
//        String itemDueDate = request.getParameter("itemDueDate");
//
//        response.setContentType("text/html");
//        PrintWriter pw = response.getWriter();
//
//        pw.write("got:");
//        pw.write(itemDescription + "<br>");
//        pw.write(itemDetails + "<br>");
//        pw.write(itemDueDate + "<br>");
//
//        Item newItem = new Item();
//        newItem.setItemDescription(itemDescription);
//        newItem.setItemDetails(itemDetails);
//        newItem.setItemDueDate(LocalDate.parse(itemDueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//
//        Data.getInstance().addItem(newItem);
//
////        response.sendRedirect("index.jsp");
//    }
}
