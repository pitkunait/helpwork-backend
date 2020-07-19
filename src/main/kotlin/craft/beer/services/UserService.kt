package craft.beer.services

import craft.beer.exceptions.CustomException
import craft.beer.model.User
import craft.beer.repositories.UserRepository
import craft.beer.security.JwtTokenProvider
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class UserService(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val jwtTokenProvider: JwtTokenProvider,
        private val authenticationManager: AuthenticationManager
) : IUserService {

    override fun signIn(username: String?, password: String?): String {
        return try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
            jwtTokenProvider.createToken(username, userRepository.findByUsername(username!!).get().roles!!)
        } catch (e: AuthenticationException) {
            throw CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    override fun signUp(user: User): String {
        return if (!userRepository.existsByUsername(user.username!!)) {
            user.password = passwordEncoder.encode(user.password)
            userRepository.save(user)
            jwtTokenProvider.createToken(user.username, user.roles!!)
        } else {
            throw CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    override fun deleteByUserName(username: String?) {
        userRepository.deleteByUsername(username)
    }

    override fun searchByUserName(username: String?): User {
        return userRepository.findByUsername(username!!).get()
    }

    override fun whoami(req: HttpServletRequest?): User {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req!!))).get()
    }

    override fun refreshToken(username: String?): String {
        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username!!).get().roles!!)
    }
}