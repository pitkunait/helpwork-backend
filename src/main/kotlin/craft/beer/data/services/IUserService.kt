package craft.beer.data.services

import craft.beer.payload.requests.SignInRequest
import craft.beer.payload.requests.SignUpRequest


interface IUserService {

    fun existsByUsername(username: String): Boolean

    fun existsByEmail(email: String): Boolean

    fun saveUser(signUpRequest: SignUpRequest)

    fun getJwtToken(signInRequest: SignInRequest): String

}