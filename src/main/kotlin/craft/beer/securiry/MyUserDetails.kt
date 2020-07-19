package craft.beer.securiry

import craft.beer.model.User
import craft.beer.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MyUserDetails (private val userRepository: UserRepository) : UserDetailsService {


    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepository.findByUsername(username).get()
                ?: throw UsernameNotFoundException("User '$username' not found")
        return org.springframework.security.core.userdetails.User //
                .withUsername(username) //
                .password(user.password) //
                .authorities(user.roles) //
                .accountExpired(false) //
                .accountLocked(false) //
                .credentialsExpired(false) //
                .disabled(false) //
                .build()
    }
}