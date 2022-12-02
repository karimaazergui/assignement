package ma.octo.assignement.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class Deposit extends Transaction {


  @Column
  private String nomPrenomEmetteur;



}
