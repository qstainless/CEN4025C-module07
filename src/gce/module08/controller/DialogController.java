package gce.module08.controller;

import gce.module08.model.Data;
import gce.module08.model.Item;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

/**
 * Controller for the Add to-do item dialog
 */
public class DialogController {

    /**
     * The to-do item's description (title)
     */
    @FXML
    private TextField itemDescriptionField;

    /**
     * The to-do item's details (if any)
     */
    @FXML
    private TextArea itemDetailsField;

    /**
     * No to-do item is complete without a due date
     */
    @FXML
    private DatePicker itemDueDateField;

    /**
     * Gets the values for the to-do item's description (title), details,
     * and due date submitted through the Add dialog, and processes the
     * data to add the item to the Data model.
     *
     * @return The Item.
     */
    public Item processResults() {
        String itemDescription = itemDescriptionField.getText().trim();

        /*
         The user may enter tabs in the itemDetails, which will generate
         an error when loading the items from the text file, because the
         application uses the tab character as a delimiter. To avoit the
         error, we replace all tab characters entered by the user with
         4 spaces.
        */
        String itemDetails = itemDetailsField.getText().trim().replace("\t", "    ");

        LocalDate itemDueDate = itemDueDateField.getValue();

        // Sets the default values for empty fields
        if (itemDescription.isEmpty()) {
            itemDescription = "[no description provided]";
        }

        if (itemDetails.isEmpty()) {
            itemDetails = "[no details provided]";
        }

        /*
         If no dueDate is selected in the DatePicker, the default dueDate
         is set to tomorrow
        */
        if (itemDueDate == null) {
            itemDueDate = LocalDate.now().plusDays(1);
        }

        /*
         We want to automatically select the newly added to-do item in
         the ListView. To do that, we first add the item to the database
         and Data model and then return it to the MainController
        */
        Item newItem = createNewItem(itemDescription, itemDetails, itemDueDate);

        insertNewItem(newItem);

        return newItem;
    }

    /**
     * Create a new to-do item with the data submitted by the user
     *
     * @param itemDescription The item description
     * @param itemDetails The item details
     * @param itemDueDate The item due date
     * @return The newly created to-=do item
     */
    public Item createNewItem(String itemDescription, String itemDetails, LocalDate itemDueDate) {
        Item newItem = new Item();

        newItem.setItemDescription(itemDescription);
        newItem.setItemDetails(itemDetails);
        newItem.setItemDueDate(itemDueDate);

        return newItem;
    }

    /**
     * Add the new item to the Data model and save to database
     *
     * @param newItem The newly created to-do item
     */
    public void insertNewItem(Item newItem) {
        Data.getInstance().addItem(newItem);
    }
}
