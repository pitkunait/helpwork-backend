package craft.beer.services

import craft.beer.model.User
import craft.beer.payload.requests.SignInRequest
import craft.beer.payload.requests.SignUpRequest
import javax.servlet.http.HttpServletRequest


interface IUserService {

    fun signIn(username: String?, password: String?): String

    fun signUp(user: User): String

    fun delete(username: String?)

    fun search(username: String?): User

    fun whoami(req: HttpServletRequest?) : User

    fun refresh(username: String?): String

}