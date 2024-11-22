package br.com.gamean.gamean.service;

import br.com.gamean.gamean.dto.TransactionDto;
import br.com.gamean.gamean.exception.ResourceNotFoundException;
import br.com.gamean.gamean.mapper.CustomModelMapper;
import br.com.gamean.gamean.model.CategoryModel;
import br.com.gamean.gamean.model.TransactionModel;
import br.com.gamean.gamean.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    public TransactionDto create(TransactionDto transactionDto){
        TransactionModel transactionModel = CustomModelMapper.parseObject(transactionDto, TransactionModel.class);
        return CustomModelMapper.parseObject(repository.save(transactionModel), TransactionDto.class);
    }

    public TransactionDto findById(long id){
        TransactionModel transactionModel = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Transação não encontrada!")
        );
        return CustomModelMapper.parseObject(transactionModel, TransactionDto.class);
    }

    public TransactionDto update(TransactionDto transactionDto){
        TransactionModel transactionModel = repository.findById(transactionDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Transação não encontrada!")
        );
        transactionModel.setName(transactionDto.getName());
        transactionModel.setDate(transactionDto.getDate());
        transactionModel.setAmount(transactionDto.getAmount());
        transactionModel.setType(transactionDto.getType());
        //parse or convert accountDto to accountModel before setting the value
        transactionModel.setCategory(CustomModelMapper.parseObject(transactionModel.getCategory(), CategoryModel.class));

        //convert the model to dto to return
        return CustomModelMapper.parseObject(repository.save(transactionModel), TransactionDto.class);
    }

    public void delete(long id){
        TransactionModel transactionModel = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Transação não encontrada!")
        );
        repository.delete(transactionModel);
    }

    public Page<TransactionDto> findAll(Pageable pageable){
        var transactions = repository.findAll(pageable);
        return transactions.map(t -> CustomModelMapper.parseObject(t, TransactionDto.class));
    }

}
