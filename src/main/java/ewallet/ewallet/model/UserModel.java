package ewallet.ewallet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "user")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    @Column(length = 25, unique = true )
    private String username;
    @Column(nullable = false)
    private String password;
    private String nama;
    @Column(nullable = false)
    private String email;
    private Long saldo;

}