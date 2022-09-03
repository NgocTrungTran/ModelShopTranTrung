package com.tnt.modelshoptrantrung.model;

import lombok.*;
import lombok.experimental.Accessors;
import com.tnt.modelshoptrantrung.model.dto.UserDTO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(name = "full_name")
    private String fullName;
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Column(precision = 12, scale = 0, updatable = false)
    private BigDecimal coin = new BigDecimal ( 0L );

    @Column(columnDefinition = "boolean default false")
    private boolean blocked = false;

    private String avatar = "avatardefault.png";

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne
    @JoinColumn(name = "location_region_id", referencedColumnName = "id")
    private LocationRegion locationRegion;

    @OneToMany(mappedBy = "user")
    private List<Deposit> deposits;

    public UserDTO toUserDTO() {
        return new UserDTO ()
                .setId(id)
                .setUsername ( username )
                .setFullName ( fullName )
                .setCoin ( coin )
                .setLocationRegion ( locationRegion.toLocationRegionDTO () )
                .setEmail ( email )
                .setPhone ( phone )
                .setAvatar ( avatar )
                .setBlocked ( blocked );
    }

}
