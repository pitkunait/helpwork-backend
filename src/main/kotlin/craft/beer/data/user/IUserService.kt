package craft.beer.data.user

interface IUserService {
    fun userExists(user: User): Boolean
}