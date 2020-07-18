package craft.beer.data.services

import craft.beer.data.user.entities.RoleEntity
import craft.beer.data.user.entities.Roles
import craft.beer.data.user.entities.UserEntity
import craft.beer.data.user.repositories.RoleRepository
import craft.beer.data.user.repositories.UserRepository
import craft.beer.payload.requests.SignInRequest
import craft.beer.payload.requests.SignUpRequest
import craft.beer.securiry.auth.AuthJwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.function.Supplier
import javax.management.relation.Role


@Service
class UserService(private val userRepository: UserRepository,
                  private val roleRepository: RoleRepository,
                  private val passwordEncoder: PasswordEncoder,
                  private val authenticationManager: AuthenticationManager,
                  private val authJwtService: AuthJwtService) : IUserService {

    override fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    override fun existsByUsername(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }

    override fun saveUser(signUpRequest: SignUpRequest) {

        val userEntity: UserEntity = UserEntity(
                username = signUpRequest.username,
                firstName = signUpRequest.firstName,
                lastName = signUpRequest.lastName,
                email = signUpRequest.email,
                password = passwordEncoder.encode(signUpRequest.password)
        )

//        val userRole: RoleEntity = roleRepository.findByName(Roles.USER)
//                .orElseThrow(Supplier { RuntimeException("Error: Role is not found.") })
//
//        val roles: HashSet<RoleEntity> = HashSet()
//        roles.add(userRole)
//        userEntity.roles = roles

        // save user
        userRepository.save(userEntity)
    }

    override fun getJwtToken(signInRequest: SignInRequest): String {
        val authentication: Authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        signInRequest.username,
                        signInRequest.password
                ))

        return authJwtService.generateJwtToken(authentication)

    }



}