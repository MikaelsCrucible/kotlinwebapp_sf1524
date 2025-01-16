package webapp

import database.Post
import database.PostsDatabase
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Response
import org.http4k.core.Status.Companion.FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.core.body.form
import org.http4k.routing.ResourceLoader
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.routing.static
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.http4k.template.FreemarkerTemplates

val app: HttpHandler =
  routes(
    "/index" bind GET to {
      val renderer = FreemarkerTemplates().HotReload("src/main/resources")
      val viewModel = IndexPage("World")
      Response(OK).body(renderer(viewModel))
    },
    "/assets" bind static(ResourceLoader.Directory("src/main/resources/assets")),
    "/submit" bind POST to { request ->
      val words = List(5) { request.form("word${it + 1}")!! }.sortedByDescending { scrabbleScore(it) }
      val renderer = FreemarkerTemplates().HotReload("src/main/resources")
      // print(words)
      // print(words.map { scrabbleScore(it) })
      val viewModel = ScorePage(words.map { it to scrabbleScore(it) })
      Response(OK).body(renderer(viewModel))
    },
    "/posts" bind GET to {
      val renderer = FreemarkerTemplates().HotReload("src/main/resources")
      val viewModel = PostsPage(PostsDatabase().loadAllPosts())
      Response(OK).body(renderer(viewModel))
    },
    "/addpost" bind POST to { request ->
      val newPost = Post(title = request.form("title")!!, body = request.form("body")!!)
      PostsDatabase().addPost(newPost)
      Response(FOUND).header("Location", "/posts")
    },
    "/deletepost" bind GET to { request ->
      PostsDatabase().deletePost(request.query("id")!!.toInt())
      Response(FOUND).header("Location", "/posts")
    }
    /*
    My code works normally but I keep getting an error(warning?) in the console when I add or delete a post

    freemarker.log._JULLoggerFactory$JULLogger error
严重: Configuration.incompatibleImprovements was set to the object returned by Configuration.getVersion(). That defeats the purpose of incompatibleImprovements, and makes upgrading FreeMarker a potentially breaking change. Also, this probably won't be allowed starting from 2.4.0. Instead, set incompatibleImprovements to the highest concrete version that's known to be compatible with your application.

    I'm not sure if I made any mistake but I assume I didn't because in the end it works lol
     */
  )

fun scrabbleScore(word: String): Int {
  val letterscore =
    mapOf(
      'A' to 1,
      'E' to 1,
      'I' to 1,
      'L' to 1,
      'N' to 1,
      'O' to 1,
      'R' to 1,
      'S' to 1,
      'T' to 1,
      'U' to 1,
      'D' to 2,
      'G' to 2,
      'B' to 3,
      'C' to 3,
      'M' to 3,
      'P' to 3,
      'F' to 4,
      'H' to 4,
      'V' to 4,
      'W' to 4,
      'Y' to 4,
      'K' to 5,
      'J' to 8,
      'X' to 8,
      'Q' to 10,
      'Z' to 10,
    )
  return word.sumOf { letter ->
    letterscore[letter.uppercaseChar()]!!
  }
}

fun main() {
  val server = app.asServer(Jetty(9000)).start()
  println("Server started on http://localhost:" + server.port())
}
