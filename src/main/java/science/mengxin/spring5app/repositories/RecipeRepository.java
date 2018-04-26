package science.mengxin.spring5app.repositories;

import org.springframework.data.repository.CrudRepository;
import science.mengxin.spring5app.domain.Recipe;

/**
 * <p>Date:    26/04/18
 *
 * @author mengxin
 * @version 1.0
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
