package science.mengxin.spring5app.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import science.mengxin.spring5app.commands.IngredientCommand;
import science.mengxin.spring5app.commands.RecipeCommand;
import science.mengxin.spring5app.services.IngredientService;
import science.mengxin.spring5app.services.RecipeService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    IngredientController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new IngredientController(recipeService, ingredientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }


    @Test
    public void listIngredients() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();

        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

        //when
        mockMvc.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));
        // then
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    public void showIngredient() throws Exception {
        //given
        IngredientCommand ingredientCommand = new IngredientCommand();

        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(),anyLong()))
                .thenReturn(ingredientCommand);
        //when
        mockMvc.perform(get("/recipe/1/ingredient/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
        // then
        verify(ingredientService, times(1))
                .findByRecipeIdAndIngredientId(anyLong(),anyLong());
    }
}