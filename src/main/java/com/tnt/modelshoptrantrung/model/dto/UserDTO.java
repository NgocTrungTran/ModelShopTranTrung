package com.tnt.modelshoptrantrung.model.dto;

import com.tnt.modelshoptrantrung.model.LocationRegion;
import com.tnt.modelshoptrantrung.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserDTO {
    private Long id;

    @NotBlank(message = "username not blank")
    @Size(min = 8, max = 20, message = "Username size between 8 and 20")
    private String username;

    @Size(min = 5, max = 30, message = "Full name size between 5 and 30")
    private String fullName;

    @Size(min = 5, max = 30,  message = "Email size between 5 and 30")
    private String email;

    @Size(min = 9, max = 10,  message = "Phone size between 9 and 10")
    private String phone;

    @Size(min = 8, max = 20,  message = "Password size between 8 and 20")
    private String password;
    private BigDecimal coin;

    private boolean blocked;

    private String avatar;

    private RoleDTO role;
    private LocationRegionDTO locationRegion;


    public UserDTO(Long id, String username, String fullName, String email, String phone, BigDecimal coin, String avatar, boolean blocked, LocationRegion locationRegion) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.coin = coin;
        this.avatar = avatar;
        this.blocked =blocked;
        this.locationRegion = locationRegion.toLocationRegionDTO ();
    }

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public User toUser() {
        return new User()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                ;
    }

    public User toAdmin() {
        return new User()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setPhone ( phone )
                .setFullName ( fullName )
                .setEmail ( email )
                .setAvatar ( avatar )
                .setLocationRegion ( locationRegion.toLocationRegion () );
    }
}
