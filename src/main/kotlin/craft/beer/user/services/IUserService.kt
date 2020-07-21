package craft.beer.user.services

import craft.beer.user.model.User
import craft.beer.controllers.payload.requests.RefreshTokenRequest
import craft.beer.controllers.payload.requests.SignInRequest
import craft.beer.controllers.payload.requests.SignUpRequest
import craft.beer.controllers.payload.responses.RefreshTokenResponse
import craft.beer.controllers.payload.responses.SignInResponse
import craft.beer.controllers.payload.responses.SignUpResponse
import craft.beer.controllers.payload.responses.UserInformationResponse
import javax.servlet.http.HttpServletRequest


interface IUserService {

    fun signIn(signInRequest: SignInRequest): SignInResponse

    fun signUp(signUpRequest: SignUpRequest): SignUpResponse

    fun deleteByUserName(username: String?)

    fun searchByUserName(username: String?): User

    fun whoami(req: HttpServletRequest?): UserInformationResponse

    fun refreshToken(refreshTokenRequest: RefreshTokenRequest): RefreshTokenResponse

}