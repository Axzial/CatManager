package fr.axzial.catmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Column(name = "cat_breed")
    private CatBreed catBreed;
    @Column(name = "cat_color")
    private String color;
    @ManyToOne
    @Column(name = "cat_owner")
    private CatOwner owner;


}
