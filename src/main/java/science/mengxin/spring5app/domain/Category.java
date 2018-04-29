package science.mengxin.spring5app.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * <p>Date:    26/04/18
 *
 * @author mengxin
 * @version 1.0
 */
@Data
@Entity
@EqualsAndHashCode(exclude = {"recipe"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipe;

    protected boolean canEqual(Object other) {
        return other instanceof Category;
    }
}
