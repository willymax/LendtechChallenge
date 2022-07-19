package com.william.lendtech.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author william makau
 * @version 1.0.0
 * Date 2022-07-19
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP) // converts back and forth between timestamp and java.util.Date
    @Column(name="transactionDateTime", nullable = false)
    private Date transactionDateTime;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Long creditUserId;
    private Long debitUserId;
    private String description;


    @PrePersist
    private void onCreate() {
        // create transaction date
        transactionDateTime = new Date();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionDateTime=" + transactionDateTime +
                ", transactionType=" + transactionType +
                ", creditUserId=" + creditUserId +
                ", debitUserId=" + debitUserId +
                ", description='" + description + '\'' +
                '}';
    }
}

