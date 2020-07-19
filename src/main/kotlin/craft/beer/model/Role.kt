package craft.beer.model

import org.springframework.security.core.GrantedAuthority
import javax.persistence.Entity

enum class Role : GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_DEVELOPER;

    override fun getAuthority(): String {
        return name
    }
}
