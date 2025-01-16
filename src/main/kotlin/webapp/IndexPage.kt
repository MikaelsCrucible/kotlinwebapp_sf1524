package webapp

import database.Post
import org.http4k.template.ViewModel

data class IndexPage(
  val name: String
) : ViewModel {
  override fun template() = "views/IndexPage.ftl"
}

data class ScorePage(
  val scores: List<Pair<String, Int>>,
) : ViewModel {
  override fun template() = "views/ScrabbleScore.ftl"
}

data class PostsPage(
  val posts: List<Post>,
) : ViewModel {
  override fun template() = "views/Posts.ftl"
}
