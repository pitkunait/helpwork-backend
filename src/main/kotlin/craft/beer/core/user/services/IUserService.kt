package craft.beer.core.user.services

import craft.beer.controllers.requests.RefreshTokenRequest
import craft.beer.controllers.requests.SignInRequest
import craft.beer.controllers.requests.SignUpRequest
import craft.beer.controllers.responses.RefreshTokenResponse
import craft.beer.controllers.responses.SignInResponse
import craft.beer.controllers.responses.SignUpResponse
import craft.beer.controllers.responses.UserInformationResponse
import craft.beer.core.user.model.User
import javax.servlet.http.HttpServletRequest


interface IUserService {

    fun signIn(signInRequest: SignInRequest): SignInResponse

    fun signUp(signUpRequest: SignUpRequest): SignUpResponse

    fun deleteByUserName(username: String?)

    fun searchByUserName(username: String?): User

    fun whoami(req: HttpServletRequest?): UserInformationResponse

    fun refreshToken(refreshTokenRequest: RefreshTokenRequest): RefreshTokenResponse

}