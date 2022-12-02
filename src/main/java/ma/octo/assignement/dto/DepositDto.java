package ma.octo.assignement.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
@Data
@EqualsAndHashCode(callSuper = true)
public class DepositDto extends TransactionDto{
    private String nomPrenomEmetteur;
}
