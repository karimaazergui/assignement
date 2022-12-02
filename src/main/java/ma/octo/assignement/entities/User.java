package ma.octo.assignement.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "UTILISATEUR")
@Data
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(length = 10, nullable = false, unique = true)
  private String username;
  @Column(length = 10, nullable = false)
  private String passWord;
  @Column()
  private boolean active;
  @Column(length = 10, nullable = false)
  private String role;
  @Column(length = 10, nullable = false)
  private String gender;

  @Column(length = 60, nullable = false)
  private String lastname;

  @Column(length = 60, nullable = false)
  private String firstname;

  @Temporal(TemporalType.DATE)
  private Date birthdate;




}
