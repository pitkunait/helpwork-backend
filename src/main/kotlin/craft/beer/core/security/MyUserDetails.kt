package craft.beer.core.security

import craft.beer.user.model.User
import craft.beer.user.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MyUserDetails(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepository.findByUsername(username).get()
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.password)
                .authorities(user.roles)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build()
    }
}