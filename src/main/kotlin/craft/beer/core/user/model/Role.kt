package craft.beer.core.user.model

import org.springframework.security.core.GrantedAuthority


enum class Role : GrantedAuthority {
    REFRESH_TOKEN,
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_DEVELOPER;

    override fun getAuthority(): String {
        return name
    }
}
