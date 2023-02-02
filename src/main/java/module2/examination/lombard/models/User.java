package module2.examination.lombard.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String username;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private int rating = 0;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private UserStatus userStatus= UserStatus.ACTIVE;


}