package br.com.gamean.gamean.service;

import br.com.gamean.gamean.dto.CategoryDto;
import br.com.gamean.gamean.exception.ResourceNotFoundException;
import br.com.gamean.gamean.mapper.CustomModelMapper;
import br.com.gamean.gamean.model.CategoryModel;
import br.com.gamean.gamean.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public CategoryDto create(CategoryDto categoryDto){
        CategoryModel category = CustomModelMapper.parseObject(categoryDto, CategoryModel.class);
        return CustomModelMapper.parseObject(repository.save(category), CategoryDto.class);
    }

    public CategoryDto findById(Long id){
        CategoryModel found = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrada")
        );
        return CustomModelMapper.parseObject(found, CategoryDto.class);
    }

    public CategoryDto update(CategoryDto categoryDto){
        CategoryModel found = repository.findById(categoryDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrada"));
        found.setName(categoryDto.getName());
        found.setDescription(categoryDto.getDescription());
        return CustomModelMapper.parseObject(repository.save(found), CategoryDto.class);
    }

    public void delete(long id){
        CategoryModel found = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrada"));
        repository.delete(found);
    }

    public List<CategoryDto> findAll(){
        var list = repository.findAll();
        return CustomModelMapper.parseObjectList(list, CategoryDto.class);
    }


}
