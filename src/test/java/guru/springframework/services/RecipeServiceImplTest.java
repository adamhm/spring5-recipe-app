package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

class RecipeServiceImplTest {
    
    AutoCloseable closeable;
    RecipeServiceImpl recipeService;
    
    @Mock
    RecipeRepository recipeRepository;
    
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    
    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }
    
    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);
        when(recipeRepository.findAll()).thenReturn(recipeData);
        Set<Recipe> recipes = recipeService.getRecipes();
        
        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }
    
    @Test
    public void getRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        
        Recipe recipeReturned = recipeService.findById(1L);
        
        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }
    
    @Test
    public void getRecipeCoomandByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);
        
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1L);
        
        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);
        
        RecipeCommand commandById = recipeService.findCommandById(1L);
        
        assertNotNull("Null recipe returned", commandById);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }
    
    @Test
    public void testDeleteById() throws Exception {
        
        //given
        Long idToDelete = Long.valueOf(2L);
        
        //when
        recipeService.deleteById(idToDelete);
        
        //no 'when', since method has void return type
        
        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
    
    @AfterEach
    public void release() throws Exception {
        closeable.close();
    }
}
