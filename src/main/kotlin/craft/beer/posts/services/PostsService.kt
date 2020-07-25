package craft.beer.posts.services

import craft.beer.controllers.requests.NewPostRequest
import craft.beer.controllers.requests.SearchPostsRequest
import craft.beer.controllers.responses.ListPostsResponse
import craft.beer.controllers.responses.NewPostResponse
import craft.beer.core.user.repositories.UserRepository
import craft.beer.posts.model.Post
import craft.beer.posts.repositories.PostRepository
import org.modelmapper.ModelMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class PostsService(private val modelMapper: ModelMapper,
                   private val postRepository: PostRepository,
                   private val userRepository: UserRepository
) : IPostsService {

    override fun newPost(newPostRequest: NewPostRequest): NewPostResponse {
        val username: String = SecurityContextHolder.getContext().authentication.name
        val user = userRepository.findByUsername(username).get()
        val post: Post = modelMapper.map(newPostRequest, Post::class.java)
        post.user = user.id
        postRepository.save(post)
        return NewPostResponse("Post created")
    }

    override fun searchPosts(page: Int, searchPostsRequest: SearchPostsRequest): ListPostsResponse {
        val posts = postRepository.findByTitleContains(searchPostsRequest.title, PageRequest.of(page, 10, Sort.by("createdAt").descending()))
        return ListPostsResponse("Posts fetched", posts)
    }

    override fun listPosts(page: Int): ListPostsResponse {
        val posts: Page<Post> = postRepository.findAll(PageRequest.of(page, 10, Sort.by("createdAt").descending()))
        return ListPostsResponse("Posts fetched", posts)
    }
}