package craft.beer.services

import craft.beer.model.User
import javax.servlet.http.HttpServletRequest


interface IUserService {

    fun signIn(username: String?, password: String?): String

    fun signUp(user: User): String

    fun deleteByUserName(username: String?)

    fun searchByUserName(username: String?): User

    fun whoami(req: HttpServletRequest?): User

    fun refreshToken(username: String?): String

}