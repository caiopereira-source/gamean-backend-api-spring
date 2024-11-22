package br.com.gamean.gamean.repository;

import br.com.gamean.gamean.model.TransactionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {

    public Page<TransactionModel> findAll(Pageable pageable);
}
