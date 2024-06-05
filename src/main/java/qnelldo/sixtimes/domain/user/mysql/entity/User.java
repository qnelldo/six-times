package qnelldo.sixtimes.domain.user.mysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String provider; // OAuth2 provider (e.g., Google)
    private String providerId; // Provider-specific ID

    public Map<String, Object> toAttributes() {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("id", id);
        attributes.put("provider", provider);
        attributes.put("providerId", providerId);
        attributes.put("name", name);
        attributes.put("email", email);
        return attributes;
    }
}
