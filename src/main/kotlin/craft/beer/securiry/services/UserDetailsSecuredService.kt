package craft.beer.securiry.services

import craft.beer.data.user.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsSecuredService(val userRepository: UserRepository) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository
                .findByUsername(username!!)
                .orElseThrow { UsernameNotFoundException("User not found: $username") }

        return UserDetailsSecured.build(user)
    }
}