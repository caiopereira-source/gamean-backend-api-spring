package br.com.gamean.gamean.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "amount", nullable = false)
    private float amount;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "type", nullable = false)
    private int type; /* 1 - Entrada \\ 2 - Saida */

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;

}
