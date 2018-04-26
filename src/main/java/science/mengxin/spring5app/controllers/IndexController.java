package science.mengxin.spring5app.controllers;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import science.mengxin.spring5app.domain.Category;
import science.mengxin.spring5app.domain.UnitOfMeasure;
import science.mengxin.spring5app.repositories.CategoryRepository;
import science.mengxin.spring5app.repositories.UnitOfMeasureRepository;

/**
 * <p>Date:    23/04/18
 *
 * @author mengxin
 * @version 1.0
 */
@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage() {
        // System.out.printf("reload quickly");

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");

        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        System.out.println("Cat Id is" + categoryOptional.get().getId());
        System.out.println("UOM Id id" + unitOfMeasureOptional.get().getId());

        return "index";
    }
}
