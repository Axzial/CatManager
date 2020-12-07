package fr.axzial.catmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The {@link CatBreed}.
 */
@Entity
@NoArgsConstructor
@Data
public class CatBreed implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_breed_id")
    private long id;
    @Column(name = "cat_breed_name", nullable = false)
    private String name;


    /**
     * Instantiates a new {@link CatBreed} with only a name.
     *
     * @param name the name
     */
    public CatBreed(String name) {
        this.name = name;
    }


    /**
     * Instantiates a new Cat breed.
     *
     * @param id   the id
     * @param name the name
     */
    public CatBreed(long id, String name) {
        this.id = id;
        this.name = name;
    }

}
