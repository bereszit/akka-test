package httpTest

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.javadsl.server.Route
import akka.http.scaladsl.Http
import akka.http.scaladsl.server
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._

import scala.concurrent.Future
import scala.io.StdIn

object MyHttp {

  def main (args: Array[String]): Unit = {

    implicit val system  = ActorSystem(Behaviors.empty, "my-system")
    implicit val executionContext = system.executionContext

    val route =
      path("hello") {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>OK</h1>"))
        }
      }

      val bindingFuture = Http().newServerAt("localhost", 8081).bind(route)

      println("Server has just started!")
      StdIn.readLine()
      bindingFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
  }
}
