package br.com.gamean.gamean.dto;

import br.com.gamean.gamean.model.CategoryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDto extends RepresentationModel<TransactionDto> {

    private long id;
    private String name;
    private float amount;
    private Date date;
    private int type; /* 1 - Entrada \\ 2 - Saida */
    private CategoryModel category;
}
