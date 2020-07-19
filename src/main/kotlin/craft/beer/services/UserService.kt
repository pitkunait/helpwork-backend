package craft.beer.services

import craft.beer.exceptions.CustomException
import craft.beer.model.User
import craft.beer.payload.requests.SignInRequest
import craft.beer.payload.requests.SignUpRequest
import craft.beer.repositories.UserRepository
import craft.beer.security.JwtTokenProvider
import org.modelmapper.ModelMapper
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
        private val authenticationManager: AuthenticationManager,
        private val modelMapper: ModelMapper
) : IUserService {

    override fun signIn(signInRequest: SignInRequest): String {
        return try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(signInRequest.username, signInRequest.password))
            jwtTokenProvider.createToken(signInRequest.username, userRepository.findByUsername(signInRequest.username).get().roles!!)
        } catch (e: AuthenticationException) {
            throw CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    override fun signUp(signUpRequest: SignUpRequest): String {
        if (userRepository.existsByUsername(signUpRequest.username)) {
            throw CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY)
        }
        if (userRepository.existsByEmail(signUpRequest.email)) {
            throw CustomException("Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY)
        }

        return try {
            val user: User = modelMapper.map(signUpRequest, User::class.java)
            user.password = passwordEncoder.encode(user.password)
            userRepository.save(user)
            jwtTokenProvider.createToken(user.username, user.roles!!)
        } catch (e: Exception) {
            throw CustomException(e.message!!, HttpStatus.UNPROCESSABLE_ENTITY)
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
        // deactivate old token if it is still valid
        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username!!).get().roles!!)
    }
}