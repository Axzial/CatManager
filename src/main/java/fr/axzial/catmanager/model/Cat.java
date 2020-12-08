package fr.axzial.catmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The {@link Cat}.
 */
@Entity
@NoArgsConstructor
@Data
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private long id;
    @Column(name = "cat_name")
    private String name;
    @ManyToOne
    private CatBreed catBreed;
    @Column(name = "cat_color")
    private String color;
    @ManyToOne
    private CatOwner owner;

    /**
     * Instantiates a new {@link Cat} with a name only.
     *
     * @param name the name
     */
    public Cat(String name) {
        this.name = name;
    }

    public Cat(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Cat(long id, String name, CatBreed catBreed, String color, CatOwner owner) {
        this.id = id;
        this.name = name;
        this.catBreed = catBreed;
        this.color = color;
        this.owner = owner;
    }
}
