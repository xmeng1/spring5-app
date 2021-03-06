package science.mengxin.spring5app.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>Date:    25/04/18
 *
 * @author mengxin
 * @version 1.0
 */
@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    //delete the reciep, then delete the ingredients by using cascade
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name="recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    // this is demo for the name and table mapping
    //@JoinTable(name="recipe_category_bar", joinColumns = @JoinColumn(name = "recipe_id_test"), inverseJoinColumns = @JoinColumn(name = "category_id_xx"))
    private Set<Category> categories = new HashSet<>();

    // ordinal will use 1,2,3 to save in the database
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
    }
}
