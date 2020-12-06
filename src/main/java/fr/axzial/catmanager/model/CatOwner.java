package fr.axzial.catmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
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
}
