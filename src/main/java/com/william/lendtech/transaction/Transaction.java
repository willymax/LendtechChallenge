package com.william.lendtech.transaction;

import com.william.lendtech.user.User;
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
    private float amount;

    @Temporal(TemporalType.TIMESTAMP) // converts back and forth between timestamp and java.util.Date
    @Column(name="transactionDateTime", nullable = false)
    private Date transactionDateTime;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "creditUserId")
    private User creditUser;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "debitUserId")
    private User debitUser;
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
                ", creditUserId=" +  (creditUser != null ? creditUser.getFirstName() : "") +
                ", debitUserId=" + (debitUser != null ? debitUser.getFirstName() : "") +
                ", description='" + description + '\'' +
                '}';
    }
}

