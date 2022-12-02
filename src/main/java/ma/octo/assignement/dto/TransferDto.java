package ma.octo.assignement.dto;

import lombok.*;


@Data
@EqualsAndHashCode(callSuper = true)
public class TransferDto extends TransactionDto{
    private String nrCompteEmetteur;


}
