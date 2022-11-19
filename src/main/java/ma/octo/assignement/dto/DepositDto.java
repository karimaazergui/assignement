package ma.octo.assignement.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class DepositDto {
    private BigDecimal montantVersement;
    private Date dateExecution;
    private String nomPrenomEmetteur;
    private String ribCompteBeneficiaire;
    private String motifVersement;
}
