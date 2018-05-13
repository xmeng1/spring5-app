package science.mengxin.spring5app.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import science.mengxin.spring5app.services.RecipeService;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    @Autowired
    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable("recipeId") String recipeId, Model model) {
        log.debug("Getting ingredient list ofr recipe id: {}", recipeId);

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";

    }
}
