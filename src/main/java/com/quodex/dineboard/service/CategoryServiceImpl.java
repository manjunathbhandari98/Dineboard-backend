package com.quodex.dineboard.service;

import com.quodex.dineboard.mapper.CategoryMapper;
import com.quodex.dineboard.dto.request.CategoryRequest;
import com.quodex.dineboard.dto.response.CategoryResponse;
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
    public List<CategoryResponse> getAllCategories(String menuId) {
        return categoryRepository.findByMenuId(menuId)
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }


    @Override
    public CategoryResponse updateCategory(String id, CategoryRequest request) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category Not Found"));
        existingCategory.setName(request.getCategoryName());
        return CategoryMapper.toResponse(categoryRepository.save(existingCategory));
    }

    @Override
    public CategoryResponse getCategoryById(String id){
        Category category =  categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));
        return CategoryMapper.toResponse(category);
    }

    @Override
    public void deleteById(String id){
        categoryRepository.deleteById(id);

    }

    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Menu menu = menuRepository.findById(request.getMenuId()).orElseThrow(()-> new RuntimeException("Menu Not Found"));
        Category category = CategoryMapper.toEntity(request,menu);
        return CategoryMapper.toResponse(categoryRepository.save(category));
    }


}
