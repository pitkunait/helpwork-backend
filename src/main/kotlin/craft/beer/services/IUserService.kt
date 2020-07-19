package craft.beer.services

import craft.beer.model.User
import craft.beer.payload.requests.SignInRequest
import craft.beer.payload.requests.SignUpRequest
import javax.servlet.http.HttpServletRequest


interface IUserService {

    fun signIn(signInRequest: SignInRequest): String

    fun signUp(signUpRequest: SignUpRequest): String

    fun deleteByUserName(username: String?)

    fun searchByUserName(username: String?): User

    fun whoami(req: HttpServletRequest?): User

    fun refreshToken(username: String?): String

}