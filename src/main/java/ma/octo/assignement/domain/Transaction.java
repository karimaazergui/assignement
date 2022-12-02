package ma.octo.assignement.domain;

import lombok.Data;
import ma.octo.assignement.domain.Account;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(precision = 16, scale = 2, nullable = false)
    private BigDecimal Montant;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateExecution;

    @ManyToOne
    private Account compteBeneficiaire;

    @Column(length = 200)
    private String motif;






}
