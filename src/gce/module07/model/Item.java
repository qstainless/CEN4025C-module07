package gce.module07.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Item.java
 *
 * Model class to represent the Item entity
 */
@Entity
@Table(name = "item")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "itemDescription", columnDefinition = "VARCHAR(255)")
    private String itemDescription;

    @Column(name = "itemDetails", columnDefinition = "TEXT")
    private String itemDetails;

    @Column(name = "itemDueDate")
    private LocalDate itemDueDate;

    /**
     * Model constructor
     */
    public Item() {
    }

    // Added overloading constructors for web ui
    public Item(Integer id, String itemDescription, String itemDetails, LocalDate itemDueDate) {
        super();
        this.id = id;
        this.itemDescription = itemDescription;
        this.itemDetails = itemDetails;
        this.itemDueDate = itemDueDate;
    }

    public Item(String itemDescription, String itemDetails, LocalDate itemDueDate) {
        super();
        this.itemDescription = itemDescription;
        this.itemDetails = itemDetails;
        this.itemDueDate = itemDueDate;
    }

    // Setters and getters
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(String itemDetails) {
        this.itemDetails = itemDetails;
    }

    public LocalDate getItemDueDate() {
        return itemDueDate;
    }

    public void setItemDueDate(LocalDate itemDueDate) {
        this.itemDueDate = itemDueDate;
    }

    /*
     By default, displaying an item's description displays the object
     reference. We want to display the actual description in the ListView
    */
    @Override
    public String toString() {
        return itemDescription;
    }
}
