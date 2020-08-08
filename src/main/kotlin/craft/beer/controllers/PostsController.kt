package craft.beer.controllers

import craft.beer.controllers.requests.NewPostRequest
import craft.beer.controllers.responses.ListPostsResponse
import craft.beer.controllers.responses.MessageResponse
import craft.beer.controllers.responses.OnePostResponse
import craft.beer.posts.services.PostsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@CrossOrigin
@RestController
class PostsController(private val postsService: PostsService) {

    @PostMapping("/posts")
    fun newPost(@RequestBody newPostRequest: NewPostRequest): ResponseEntity<MessageResponse> {
        return try {
            postsService.newPost(newPostRequest)
            ResponseEntity
                    .ok()
                    .body(MessageResponse("Post created!"))
        } catch (e: Exception) {
            ResponseEntity
                    .badRequest()
                    .body(MessageResponse(e.message!!))
        }
    }

    @GetMapping("/posts", params = ["page"])
    fun listPosts(@RequestParam(name = "page") page: Int): ResponseEntity<ListPostsResponse> {
        return try {
            val posts = postsService.listPosts(page)
            ResponseEntity
                    .ok()
                    .body(ListPostsResponse("Posts fetched", posts))
        } catch (e: Exception) {
            ResponseEntity
                    .badRequest()
                    .body(ListPostsResponse(e.message!!))
        }
    }

    @GetMapping("/posts/{id}")
    fun getOnePost(@PathVariable id: Int): ResponseEntity<OnePostResponse> {
        return try {
            val post = postsService.getOnePost(id)
            ResponseEntity
                    .ok()
                    .body(OnePostResponse("Post fetched", post))
        } catch (e: Exception) {
            ResponseEntity
                    .badRequest()
                    .body(OnePostResponse(e.message!!))
        }
    }

    @PutMapping("/posts/{id}")
    fun editPost(@PathVariable id: Int, @RequestBody newPostRequest: NewPostRequest): ResponseEntity<MessageResponse> {
        return try {
            postsService.editPost(id, newPostRequest)
            ResponseEntity
                    .ok()
                    .body(MessageResponse("Post edited!"))
        } catch (e: Exception) {
            ResponseEntity
                    .badRequest()
                    .body(MessageResponse(e.message!!))
        }
    }

    @DeleteMapping("/posts/{id}")
    fun deletePost(@PathVariable id: Int): ResponseEntity<MessageResponse> {
        return try {
            postsService.deletePost(id)
            ResponseEntity
                    .ok()
                    .body(MessageResponse("Post deleted!"))
        } catch (e: Exception) {
            ResponseEntity
                    .badRequest()
                    .body(MessageResponse(e.message!!))
        }
    }

    @GetMapping("/posts", params = ["page", "title"])
    fun searchPosts(@RequestParam(name = "page") page: Int,
                    @RequestParam(name = "title") title: String): ResponseEntity<ListPostsResponse> {
        return try {
            val posts = postsService.searchPosts(page, title)
            ResponseEntity
                    .ok()
                    .body(ListPostsResponse("Posts fetched", posts))
        } catch (e: Exception) {
            ResponseEntity
                    .badRequest()
                    .body(ListPostsResponse(e.message!!))
        }
    }
}