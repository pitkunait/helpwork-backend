package craft.beer.model

import org.springframework.security.core.GrantedAuthority


enum class Token : GrantedAuthority {
    REFRESH_TOKEN;

    override fun getAuthority(): String {
        return name
    }
}
