package gce.module08.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Item.java
 *
 * Model class to represent the Item entity
 */
@Entity
@Table(name = "item")
public class Item {
    private Integer id;
    private String itemDescription;
    private String itemDetails;
    private LocalDate itemDueDate;

    /**
     * Model constructor
     */
    public Item() {
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "itemDescription", columnDefinition = "VARCHAR(255)")
    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Basic
    @Column(name = "itemDetails", length = -1, columnDefinition = "TEXT")
    public String getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(String itemDetails) {
        this.itemDetails = itemDetails;
    }

    @Basic
    @Column(name = "itemDueDate", nullable = true)
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
