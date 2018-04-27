package science.mengxin.spring5app.controllers;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import science.mengxin.spring5app.domain.Category;
import science.mengxin.spring5app.domain.UnitOfMeasure;
import science.mengxin.spring5app.repositories.CategoryRepository;
import science.mengxin.spring5app.repositories.UnitOfMeasureRepository;
import science.mengxin.spring5app.services.RecipeService;

/**
 * <p>Date:    23/04/18
 *
 * @author mengxin
 * @version 1.0
 */
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model) {

        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
