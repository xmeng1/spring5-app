package science.mengxin.spring5app.services;

import science.mengxin.spring5app.commands.RecipeCommand;
import science.mengxin.spring5app.domain.Recipe;

import java.util.Set;

//@Service
public interface RecipeService {
    Set<Recipe> getRecipes();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);
}
