package science.mengxin.spring5app.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import science.mengxin.spring5app.commands.IngredientCommand;
import science.mengxin.spring5app.converters.IngredientToIngredientCommand;
import science.mengxin.spring5app.domain.Recipe;
import science.mengxin.spring5app.repositories.RecipeRepository;

import java.util.Optional;


@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            log.error("recipe id cannot found. Id: {}", recipeId);

        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional
                = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert).findFirst();

        if (!ingredientCommandOptional.isPresent()) {
            log.error("Ingredient id cannot found. Id: {}", ingredientId);
        }

        return ingredientCommandOptional.get();

    }
}
