import akka.actor.Actor

case class ProcessStringMsg(str: String)
case class StringProcessedMsg(words: Integer)

class StringCounterActor extends Actor {
  def receive = {
    case ProcessStringMsg(string) =>
      val wordsInLine = string.trim.split(" ").length
      println(s"Line-> $string ||| words in line-> $wordsInLine")
      sender ! StringProcessedMsg(wordsInLine)

    case _ => println("Error: message not recognized!")
  }
}
