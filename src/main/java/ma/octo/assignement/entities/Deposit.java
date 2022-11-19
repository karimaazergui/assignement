package ma.octo.assignement.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "DEP")
@Data
public class Deposit {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(precision = 16, scale = 2, nullable = false)
  private BigDecimal Montant;

  @Column
  @Temporal(TemporalType.TIMESTAMP)
  private Date dateExecution;

  @Column
  private String nomPrenomEmetteur;

  @ManyToOne
  private Account compteBeneficiaire;

  @Column(length = 200)
  private String motifDeposit;


}
