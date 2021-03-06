import akka.actor.{ActorSystem, Props}

import scala.language.postfixOps

object Main extends App {

  import akka.util.Timeout
  import scala.concurrent.duration._
  import akka.pattern.ask
  import akka.dispatch.ExecutionContexts._

  implicit val ac = global

  def start (args: Array[String]): Unit = {
    val system = ActorSystem("StringCounterSystem")
    val actor = system.actorOf(Props(new WordCounterActor(args(0))))

    implicit val timeout = Timeout(25 seconds)
    val future = actor ? StartProcessFileMsg()

    future.map { result =>
      println("Total number of words " + result)
      system.terminate
    }
  }

  start(args)
}
