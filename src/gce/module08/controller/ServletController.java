package gce.module08.controller;

import gce.module08.model.Item;
import gce.module08.model.ItemCrud;
import gce.module08.model.UnsafeItemCrud;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Servlet controller for all actions in the application's web UI
 */
@WebServlet("/")
public class ServletController extends HttpServlet {
    private static final long serialVersionUID = 127517L;

    // Using unsafe SQl queries for Module 11 Assignment
    // Uncomment and delete the following after submission
     private ItemCrud itemCrud; // uncomment
//    private UnsafeItemCrud itemCrud; // delete

    /**
     * Creates an item from the form data submitted by the user
     *
     * @param request The submitted form data
     * @return The created item
     */
    private static Item processFormData(HttpServletRequest request) {
        String itemDescription = request.getParameter("itemDescription");
        String itemDetails = request.getParameter("itemDetails");
        String itemDueDate = request.getParameter("itemDueDate");

        Item submittedItem = new Item();
        submittedItem.setItemDescription(itemDescription);
        submittedItem.setItemDetails(itemDetails);
        submittedItem.setItemDueDate(LocalDate.parse(itemDueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        return submittedItem;
    }

    /**
     * Initializes the servlet controller
     */
    @Override
    public void init() {
        // Using unsafe SQl queries for Module 11 Assignment
        // Uncomment and delete the following after submission
         itemCrud = new ItemCrud(); // uncomment
//        itemCrud = new UnsafeItemCrud(); // delete
    }

    /**
     * Processes the POST data
     *
     * @param request  The POST request
     * @param response The servlet's response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Processes the POST action to make the method calls required for CRUD
     * operations on the Item entity
     *
     * @param request  The POST request
     * @param response The servlet's response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/insert":
                insertItem(request, response);
                break;
            case "/edit":
                editItemForm(request, response);
                break;
            case "/update":
                updateItem(request, response);
                break;
            case "/confirm":
                confirmDeletion(request, response);
                break;
            case "/delete":
                deleteItem(request, response);
                break;
            default:
                listItem(request, response);
                break;
        }
    }

    /**
     * Calls for insertion of the submitted item into the database
     *
     * @param request  The POST request
     * @param response The servlet's response
     * @throws IOException
     */
    private void insertItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Item newItem = processFormData(request);

        itemCrud.insertItem(newItem);

        System.out.println("Inserting: " + newItem.getItemDescription()
                + " / " + newItem.getItemDetails()
                + " / " + newItem.getItemDueDate() + "\n"
        );

        response.sendRedirect("list");
    }

    /**
     * Calls the item edit form to be displayed in the web UI
     *
     * @param request  The POST request
     * @param response The servlet's response
     * @throws ServletException
     * @throws IOException
     */
    private void editItemForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Item selectedItem = itemCrud.selectItem(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-item.jsp");

        request.setAttribute("item", selectedItem);

        dispatcher.forward(request, response);
    }

    /**
     * Calls for an update of the edited item in the database
     *
     * @param request  The POST request
     * @param response The servlet's response
     * @throws IOException
     */
    private void updateItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Item updatedItem = processFormData(request);

        updatedItem.setId(Integer.parseInt(request.getParameter("id")));

        itemCrud.updateItem(updatedItem);

        response.sendRedirect("list");
    }

    /**
     * Requests user confirmation to delete the selected item
     *
     * @param request  The POST request
     * @param response The servlet's response
     * @throws ServletException
     * @throws IOException
     */
    private void confirmDeletion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Item selectedItem = itemCrud.selectItem(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("confirm-deletion.jsp");

        request.setAttribute("item", selectedItem);

        dispatcher.forward(request, response);
    }

    /**
     * Calls for deletion of the selected item on the database
     *
     * @param request  The POST request
     * @param response The servlet's response
     * @throws IOException
     */
    private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        System.out.println("Deleting: Item id " + id + " => " + itemCrud.selectItem(id).getItemDescription() + "\n");

        itemCrud.deleteItemById(id);

        response.sendRedirect("list");
    }

    /**
     * Queries the items in the database and calls for the items to be
     * displayed in the web UI
     *
     * @param request  The POST request
     * @param response The servlet's response
     * @throws IOException
     * @throws ServletException
     */
    private void listItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Item> items = itemCrud.loadAllItems();

        request.setAttribute("items", items);

        RequestDispatcher dispatcher = request.getRequestDispatcher("list-items.jsp");

        dispatcher.forward(request, response);
    }
}
