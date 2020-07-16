package craft.beer.securiry

import com.fasterxml.jackson.annotation.JsonIgnore
import craft.beer.data.user.entities.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

class UserDetailsSecured(val id: Long?,
                         private val username: String?,
                         val email: String?,
                         @field:JsonIgnore private val password: String?,
                         private val authorities: Collection<GrantedAuthority>) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String? {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val user = other as UserDetailsSecured
        return id == user.id
    }

    companion object {
        private const val serialVersionUID = 1L
        fun build(user: UserEntity): UserDetailsSecured {
            val authorities: List<GrantedAuthority> = user.roles.stream()
                    .map { role -> SimpleGrantedAuthority(role.getName()?.name) }
                    .collect(Collectors.toList())
            return UserDetailsSecured(
                    user.id,
                    user.username,
                    user.email,
                    user.password,
                    authorities)
        }
    }

}