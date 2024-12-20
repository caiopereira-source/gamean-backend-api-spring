package br.com.gamean.gamean.repository;

import br.com.gamean.gamean.model.CategoryModel;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
}
