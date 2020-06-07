## To-do web application using Hibernate and Tomcat

Guillermo Castaneda Echegaray

CEN 4025C-33718

Dr. Dhrgam AL Kafaf


### Application structure
```
/src

    /gce

        /module02
            
            Main.java - The application's point of entry
            
            /model
add             ItemDao.java - Interface for the Item model

refactor        Data.java - Singleton class to store the to-do items in memory
                            and create, read, and delete operations to the 
                            database

refactor        Item.java - Class to store the elements of each to-do item

add             ItemCrud.java - Class to perform database CRUD operations 

move            hibernate.cfg.xml - The hibernate configuration file

add             item.xml - Item entity configuration for persistence 

            /view
                MainStage.java - The applications main window layout

                MainDialog.java - The dialog for adding new to-do items

            /controller
                MainController.java - Handles the functions of the main stage

refactor        DialogController.java - Handles the functions of the main dialog

refactor        HibernateController.java - Handles JDBC sessions

add             ServletController.java - Handles all actions in the application's web UI
```

### hibernate.cfg.xml
```
Set session-factory properties for the hibernate-configuration
    dialect
    driver_class
    connection.url (fix for potential serverTimezone error)
    database username
    database password
    character enconding
    use unicode (true)
    show sql (true for debugging)
    format sql (true for debugging)
    hbm2ddl (set to update)
    map to Item model

```

### Main.java
```
public class Main extends JavaFX application

    public static void main
        launch the application

    public void init
        get the singleton instance
        load to-do items from the database

    public void start
        use FXMLLoader to load the fxml file with the main stage GUI
        set the primary stage title
        set the primary stage scene dimensions
        show the primary stage on the screen

    Remove public void stop method
```

### model
#### Item.java
```
Include Hibernate annotations

public class Item
    Entity linked to "item" table
    implement serializable

    Variable decalrations:
        Integer id (auto-increment IDENTITY)
        String itemDescription VARCHAR(255)
        String itemDetails TEXT
        LocalDate itemDueDate DATE
        move annotations to getters

    public Item()
        Do not pass paramenters. Use setters instead per hibernate

    public Integer getId
        return the value of the row's id

    public void setId
        sets the value of id

    public String getItemDescription
        returns the value of itemDescription

    public void setItemDescription(String itemDescription)
        sets the itemDescription value to the object

    public String getItemDetails
        returns the value of itemDetails

    public void setItemDetails(String itemDetails)
        sets the itemDetails value to the object

    public LocalDate getItemDueDate
        returns the value of itemDueDate

    public void setItemDueDate(LocalDate itemDueDate)
        sets the itemDueDate value to the object

    Override the Java toString method
    public String toString()
        returns the String value of itemDescription
```

#### Data.java
```
public class Data

    Variable declarations
        ObservableList where the to-do items will be stored in memory

    private Data
        Singleton constructor initialize ItemCrud for database crud operations

    public static Data getInstance
        returns the singleton instance

    public ObservableList<Item> getItems()
        returns the items in the to-do list

    public void addItem(Item item)
        add the passed item to the database
        if item is successfully added to the database
            add the passed item to the Data model
        else
            rollback the database transaction
            do not add item to the Data model

    public void loadItems
        instantiate items as an observable array list in FXCollections

        open the hibernate session
        create the CriteriaBuilder
        create the CriteriaQuery
        set the root for the CriteriaQuery
        select the CriteriaQuery root
        get the query result list
        add the result list to a List of Item
        populate the observable array list with the query results

    public void deleteItem(Item item)
        delete the passed item from the database
        if the item is successfully deleted from the database
            use the remove() method to remove the passed item from the Data model
        else
            rollback the database transaction
```
#### ItemDao.java
```
public interface ItemDao
    delcare the methods that will be implemented in ItemCrud.java
```

#### ItemCrud.java 

```
public class ItemCrud implements ItemDao

    declare sessiona and transaction variables

    public ItemCrud()
        empty constructor

    public void insertItem(Item item)
        get the hibernate session factory
        save the newly created item in a hibernate transaction
        if no exceptions are thrown
            close the the session factory
        else
            Display the stack trace for debugging

    public List<Item> loadAllItems() 
        get the hibernate session factory
        create the CriteriaBuilder
        create the CriteriaQuery
        set the root for the CriteriaQuery
        select the CriteriaQuery root
        get the query result list
        add the result list to a List of Item
        if no exceptions are thrown
            close the the session factory
        else
            Display the stack trace for debugging
        return the list to display in the web UI

    public void deleteItem(Item item)
        get the hibernate session factory
        delete the selected item in a hibernate transaction
        if no exceptions are thrown
            close the the session factory
        else
            Display the stack trace for debugging

    public void deleteItemById(int itemId)
        select an item using the passed id
        if the item exists
            delete the item
    
    public Item selectItem(int itemId)
        get the hibernate session factory
        select an item using the passed id
        if no exceptions are thrown
            close the the session factory
        else
            Display the stack trace for debugging
        return the selected item

    public void updateItem(Item item)
        get the hibernate session factory
        update the selected item in a hibernate transaction
        if no exceptions are thrown
            close the the session factory
        else
            Display the stack trace for debugging

    private void closingError(Exception e)
        display an error message and stack on the console
```

### view
#### MainStage.fxml
```
    Borderpage divided into four sections
    Top Menu bar
        File Menu
            Exit 
        Edit Menu
            Add new to-do list item
            Delete selected to-do list item

    Left
        Display the ListView of all to-do list items

    Center
        Vertical box
            Horizontal box
                Display the selected item's due date
            Horizontal box
                Display the selected item's details
```

#### MainDialog.fxml
```
    Dialog pane
        add a GridPane with 2 columns 3 rows
            Row to-do item description (title)
            Row to-do item details
            Row to-do item due date DatePicker

        add OK and Cancel buttons
```

### controller
#### MainController.java
```
public class MainController

    FXML annotated object declarations
        private BorderPane mainBorderPane;
        private ListView<Item> todoListView;
        private Text itemDetailsText;
        private Label dueDateLabel;

    FXML annotated methods
        public void newItemDialog
            declare and instantiate the dialog for user input
            assign dialog as a child of the main stage
            set the dialog title
            use FXMLLoader to load the MainDialog.fxml file
            set the dialog content from the fxml file
            add the OK and CANCEL buttons to the dialog
            use Optional showAndWait to focus on the dialog and ignore other windows

            if the OK button is pressed
                get the dialog controller to allow access to its methods
                call the Item processResults method
                select the newly created item on the ListView

        public void handleMenuDelete
            declare the selected item from the todoListView
            if the selectedItem is not null
                delete the selected item by calling the deleteItem method

        public void programExit
            exit the program with exit code 0

    Non-annotated methods
        public void initialize
            Call method to populate the ListView with the to-do items in the database

        public void populateListView
            listen to events to display to-do list items and select the newly added item

            if a new item is added and its value is not null
                select the new item in the ListView
                format the due date with LONG FormatStyle (e.g., May 15, 2020)
                display the item's due date in the dueDateLabel
                display the item's details in the itemDetailsText
            else
                display the empty item's due date as null (empty)
                display the empty item's details as null (empty)

            Populate the list view with the to-do items in the Data model
            Ensure that we can only select one item at a time
            Select the first to-do item in the list

        public void deleteItem(Item item)
            Create a confirmation alert prior to deletion of the selected item

            if the OK button is pressed
                Call the singleton to delete the selected item from the Data model
```

#### DialogController.java
```
public class DialogController

    FXML annotated object declarations
        private TextField itemDescriptionField;
        private TextArea itemDetailsField;
        private DatePicker itemDueDateField;

    public Item processResults
        gets the values entered by the user in the dialog
            the item's description (title) and trims the whitespace off its content 
            the item's details, trims the whitespace and replaces tab charatcers with 4 spaces
            the item's due date value

        set default values for empty fields
        set tomorrow's date if no due date is selected in the DatePicker

        populate the newItem with the Item model setters
        add the newly created item to the database and the Data model

        return the newly created item
```

#### HibernateController.java
```
public class HibernateController
    SessionFactory declaration

    static constructor to try and load the session factory automatically

    public static void loadSessionFactory
        load the hibernate configuration file
        add the annotated class Item to the configuration

        create a new ServiceRegistry
        build the SessionFactory

    public static Session getSession
        opens a session
        if the session opens successfully
            return the opened session
```

#### ServletController.java
```
public class ServletController extends HttpServlet
    declare the serialVersionUID and ItemCrud variables

    private static Item processFormData(HttpServletRequest request)
        get parameters passed in the POST variable
        instantiate an Item object with those parameters
        return the Item object

    public void init()
        instantiate the ItemCrud class

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
        call the doGet method to process the POST request

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
        get the requested action
        call the appropriate method based on the requested action

    private void insertItem(HttpServletRequest request, HttpServletResponse response) throws IOException
        call the processFormData method to get the POST content
        create a new Item object with the POST content
        call ItemCrud to insert the Item object into the database
        go back to show the list of to-do items

    private void editItemForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
        select an Item object by its id
        open the edit form page

    private void updateItem(HttpServletRequest request, HttpServletResponse response) throws IOException
        call the processFormData method to get the POST content
        create a new Item object with the updated POST content
        call ItemCrud to update the selected Item object in the database
        go back to show the list of to-do items

    private void deleteItem(HttpServletRequest request, HttpServletResponse response) throws IOException
        get the id of the selected Item to delete
        call ItemCrud to delete the item from the database
        go back to show the list of to-do items

    private void listItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
        create a List object of Item
        call ItemCrud to query the database for all items
        show the list of to-do items
```
