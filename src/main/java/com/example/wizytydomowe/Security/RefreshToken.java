package com.example.wizytydomowe.Security;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class RefreshToken {
    @Id
    private String token;
    private String username;
    private Date expirationDate;
}
