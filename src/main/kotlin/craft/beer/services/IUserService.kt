package craft.beer.services

import craft.beer.model.User
import craft.beer.payload.requests.RefreshTokenRequest
import craft.beer.payload.requests.SignInRequest
import craft.beer.payload.requests.SignUpRequest
import craft.beer.payload.responses.RefreshTokenResponse
import craft.beer.payload.responses.SignInResponse
import craft.beer.payload.responses.SignUpResponse
import craft.beer.payload.responses.UserInformationResponse
import javax.servlet.http.HttpServletRequest


interface IUserService {

    fun signIn(signInRequest: SignInRequest): SignInResponse

    fun signUp(signUpRequest: SignUpRequest): SignUpResponse

    fun deleteByUserName(username: String?)

    fun searchByUserName(username: String?): User

    fun whoami(req: HttpServletRequest?): UserInformationResponse

    fun refreshToken(refreshTokenRequest: RefreshTokenRequest): RefreshTokenResponse

}