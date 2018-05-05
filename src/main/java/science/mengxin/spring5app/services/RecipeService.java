package science.mengxin.spring5app.services;

import org.springframework.stereotype.Service;
import science.mengxin.spring5app.domain.Recipe;

import java.util.Set;

//@Service
public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long l);
}
