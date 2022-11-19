package ma.octo.assignement.entities;

import lombok.Data;
import ma.octo.assignement.entities.util.EventType;

import javax.persistence.*;

@Entity
@Table(name = "AUDIT")
@Data
public class Audit {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 100)
  private String message;

  @Enumerated(EnumType.STRING)
  private EventType eventType;


}
