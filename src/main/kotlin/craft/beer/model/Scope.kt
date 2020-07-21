package craft.beer.model

import org.springframework.security.core.GrantedAuthority


enum class Scope : GrantedAuthority {
    REFRESH_TOKEN;

    override fun getAuthority(): String {
        return name
    }
}
