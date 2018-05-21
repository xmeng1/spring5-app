package science.mengxin.spring5app.repositories;

import org.springframework.data.repository.CrudRepository;
import science.mengxin.spring5app.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
