package science.mengxin.spring5app.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import science.mengxin.spring5app.commands.IngredientCommand;
import science.mengxin.spring5app.services.IngredientService;
import science.mengxin.spring5app.services.RecipeService;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }


    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable("recipeId") String recipeId, Model model) {
        log.debug("Getting ingredient list ofr recipe id: {}", recipeId);

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";

    }


    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(
            @PathVariable("recipeId") String recipeId,
            @PathVariable("ingredientId") String ingredientId,
            Model model) {
        log.debug("Show the ingredient");
        IngredientCommand ingredientCommand = ingredientService
                .findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId));
        model.addAttribute("ingredient",  ingredientCommand);
        return "recipe/ingredient/show";
    }
}
