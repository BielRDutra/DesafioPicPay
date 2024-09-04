package com.picpaysimplificado.picpaysimplificado.services;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.picpaysimplificado.repositories.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;


    @Autowired
    private AuthorizationService authService;

    @Autowired
    private NotificationService notificationService;


    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.sender_Id());
        User receiver = this.userService.findUserById(transaction.receiver_Id());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = this.authService.authorizeTransaction(sender, transaction.value());
        /* Não entendi o ! */
        if(!isAuthorized){
            throw new Exception("Transação não autorizada");

        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

    this.notificationService.sendNotification(sender,"Transação realizada com sucesso");
    this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");

    return newTransaction;
    }
}
