package ma.octo.assignement.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class TransactionDto {
    private String nrCompteBeneficiaire;
    private String motif;
    private BigDecimal montant;
    private Date date;
}
