package craft.beer.data.user

import org.springframework.stereotype.Service

@Service
class UserService: IUserService {
    override fun userExists(user: User) : Boolean {
        // check in db
        return true;
    }
}