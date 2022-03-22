import akka.actor.{Actor, ActorRef, Props}

case class StartProcessFileMsg()

class WordCounterActor (fileName: String) extends Actor {
  private var running = false
  private var totalLines = 0
  private var linesProcessed = 0
  private var result = 0
  private var fileSender: Option[ActorRef] = None


  def receive = {
    case StartProcessFileMsg() =>
      if(running) println("Warning: duplicate start message received!")
      else {
        running = true
        fileSender = Some(sender)
        import scala.io.Source._
        fromFile(fileName).getLines.foreach { line =>
          context.actorOf(Props[StringCounterActor]) ! ProcessStringMsg(line)
          totalLines += 1
        }
      }

    case StringProcessedMsg(words) =>
      result += words
      linesProcessed +=1
      if(linesProcessed == totalLines) fileSender.map(_ ! result)

    case _ => println("Message not recognized!")
  }
}
