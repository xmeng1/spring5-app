package science.mengxin.spring5app.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import science.mengxin.spring5app.commands.IngredientCommand;
import science.mengxin.spring5app.converters.IngredientCommandToIngredient;
import science.mengxin.spring5app.converters.IngredientToIngredientCommand;
import science.mengxin.spring5app.domain.Ingredient;
import science.mengxin.spring5app.domain.Recipe;
import science.mengxin.spring5app.repositories.RecipeRepository;
import science.mengxin.spring5app.repositories.UnitOfMeasureRepository;

import java.util.Optional;


@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {



    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand, IngredientCommandToIngredient ingredientCommandToIngredient, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()) {
            //TODO: need to handler the error
            log.error("recipe id cannot found. Id: {}", recipeId);

        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional
                = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientToIngredientCommand::convert).findFirst();

        if (!ingredientCommandOptional.isPresent()) {
            //TODO: need to handler the error
            log.error("Ingredient id cannot found. Id: {}", ingredientId);
        }

        return ingredientCommandOptional.get();

    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if(!recipeOptional.isPresent()){

            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                if (command.getUnitOfMeasure() != null) {
                    ingredientFound.setUom(unitOfMeasureRepository
                            .findById(command.getUnitOfMeasure().getId())
                            .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
                }
            } else {
                //add new Ingredient
                recipe.addIngredient(ingredientCommandToIngredient.convert(command));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            //to do check for fail
            return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst()
                    .get());
        }
    }
}
