package br.com.gamean.gamean.controller;

import br.com.gamean.gamean.dto.CategoryDto;
import br.com.gamean.gamean.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable(name = "id") long id){
        CategoryDto category = service.findById(id);
        this.buildSelfLink(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<CategoryDto>> findAll(){
        CollectionModel<CategoryDto> categories  = CollectionModel.of(service.findAll());
        for(CategoryDto category : categories){
            buildSelfLink(category);
        }
        this.buildCollectionLink(categories);
        return new ResponseEntity<CollectionModel<CategoryDto>>(categories, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto){
        CategoryDto category = service.create(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryDto, @PathVariable(name = "id") long id) {
        categoryDto.setId(id);
        CategoryDto category = service.update(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") long id) {
        service.delete(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


    private void buildSelfLink(CategoryDto categoryDto){
        //self link
        categoryDto.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()).findById(categoryDto.getId())
                ).withSelfRel()
        );
    }

    public void buildCollectionLink(CollectionModel<CategoryDto> categories){
        categories.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()).findAll()
                ).withRel(LinkRelation.of("COLLECTION"))
        );
    }

}

