package fr.axzial.catmanager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@link CatOwner}
 */
@Entity
@Data
public class CatOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_owner_id")
    private long id;
    @Column(name = "cat_owner_name")
    private String name;
    @OneToMany
    @Column(name = "cat_owner_cats")
    private List<Cat> catList;

    /**
     * Add {@link Cat} to the catList.
     *
     * @param cat the cat
     */
    public void addCat(Cat cat) {
        catList.add(cat);
    }

    /**
     * Instantiates a new {@link CatOwner}
     */
    public CatOwner() {
        catList = new ArrayList<>();
    }

    public CatOwner(long id, String name) {
        this.id = id;
        this.name = name;
        catList = new ArrayList<>();
    }

    /**
     * Instantiates a new {@link CatOwner} with a name only
     *
     * @param name the name
     */
    public CatOwner(String name) {
        this.name = name;
        catList = new ArrayList<>();
    }
}
