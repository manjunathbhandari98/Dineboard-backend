package com.quodex.dineboard.service;

import com.quodex.dineboard.dto.CategoryDTO;
import com.quodex.dineboard.model.Category;
import com.quodex.dineboard.model.Menu;
import com.quodex.dineboard.repository.CategoryRepository;
import com.quodex.dineboard.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Override
    public List<CategoryDTO> getAllCategories(String menuId) {
        return categoryRepository.findByMenuId(menuId)
                .stream()
                .map(Category::toDTO)
                .toList();
    }


    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not Found"));
        existingCategory.setName(categoryDTO.getName());
        return categoryRepository.save(existingCategory).toDTO();
    }

    @Override
    public CategoryDTO getCategoryById(Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not Found")).toDTO();
    }

    @Override
    public void deleteById(Long id){
        categoryRepository.deleteById(id);

    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Menu menu = menuRepository.findById(categoryDTO.getMenuId()).orElseThrow(()-> new RuntimeException("Menu Not Found"));
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setMenu(menu);
        return categoryRepository.save(category).toDTO();
    }


}
