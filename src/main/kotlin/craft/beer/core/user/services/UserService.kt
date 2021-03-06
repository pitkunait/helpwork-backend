package craft.beer.core.user.services

import craft.beer.controllers.requests.RefreshTokenRequest
import craft.beer.controllers.requests.SignInRequest
import craft.beer.controllers.requests.SignUpRequest
import craft.beer.controllers.responses.RefreshTokenResponse
import craft.beer.controllers.responses.SignInResponse
import craft.beer.controllers.responses.SignUpResponse
import craft.beer.controllers.responses.UserInformationResponse
import craft.beer.core.exceptions.AuthException
import craft.beer.core.security.jwt.JwtTokenProvider
import craft.beer.core.user.model.User
import craft.beer.core.user.repositories.UserRepository
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

    override fun signIn(signInRequest: SignInRequest): SignInResponse {
        return try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(signInRequest.username, signInRequest.password))
            val accessToken: String = jwtTokenProvider.createAccessJwtToken(signInRequest.username, userRepository.findByUsername(signInRequest.username).get().roles!!)
            val refreshToken: String = jwtTokenProvider.createRefreshJwtToken(signInRequest.username)
            SignInResponse("Success: User logged in.", accessToken, refreshToken)
        } catch (e: AuthenticationException) {
            throw AuthException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    override fun signUp(signUpRequest: SignUpRequest): SignUpResponse {
        if (userRepository.existsByUsername(signUpRequest.username)) {
            throw AuthException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY)
        }
        if (userRepository.existsByEmail(signUpRequest.email)) {
            throw AuthException("Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY)
        }

        return try {
            val user: User = modelMapper.map(signUpRequest, User::class.java)
            user.password = passwordEncoder.encode(user.password)
            userRepository.save(user)
            val accessToken: String = jwtTokenProvider.createAccessJwtToken(user.username, user.roles!!)
            val refreshToken: String = jwtTokenProvider.createRefreshJwtToken(user.username)
            SignUpResponse("Success: New user created.", accessToken, refreshToken)
        } catch (e: Exception) {
            throw AuthException(e.message!!, HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    override fun refreshToken(refreshTokenRequest: RefreshTokenRequest): RefreshTokenResponse {
        return try {
            jwtTokenProvider.validateToken(refreshTokenRequest.refreshJwt)
            val username: String = jwtTokenProvider.getUsername(refreshTokenRequest.refreshJwt)
            val accessToken: String = jwtTokenProvider.createAccessJwtToken(username, userRepository.findByUsername(username).get().roles!!)
            RefreshTokenResponse("Success: New token granted.", accessToken)
        } catch (e: Exception) {
            throw AuthException(e.message!!, HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    override fun whoami(req: HttpServletRequest?): UserInformationResponse {
        val user: User = userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req!!))).get()
        return modelMapper.map(user, UserInformationResponse::class.java)
    }

    override fun deleteByUserName(username: String?) {
        userRepository.deleteByUsername(username)
    }

    override fun searchByUserName(username: String?): User {
        return userRepository.findByUsername(username!!).get()
    }


}