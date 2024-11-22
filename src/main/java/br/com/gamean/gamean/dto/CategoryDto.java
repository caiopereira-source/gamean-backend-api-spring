package br.com.gamean.gamean.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CategoryDto extends RepresentationModel<CategoryDto> {

    private long id;
    private String name;
    private String description;
}
