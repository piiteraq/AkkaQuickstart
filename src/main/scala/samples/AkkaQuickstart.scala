package samples

import akka.actor.{ActorRef, ActorSystem}

import scala.io.StdIn

object AkkaQuickstart extends App {
  import Greeter._

  // Create the 'helloAkka' actor system
  val system: ActorSystem = ActorSystem("helloAkka")
  //println(system.settings) // Prints out the merged configuration settings

  try {
    // Create the printer actor
    val printer: ActorRef = system.actorOf(Printer.props, "printerActor")

    // Create the 'greeter' actors
    val howdyGreeter: ActorRef =
      system.actorOf(Greeter.props("Howdy", printer), "howdyGreeter")
    val helloGreeter: ActorRef =
      system.actorOf(Greeter.props("Hello", printer), "helloGreeter")
    val goodDayGreeter: ActorRef =
      system.actorOf(Greeter.props("Good day", printer), "goodDayGreeter")

    howdyGreeter ! WhoToGreet("Akka")
    howdyGreeter ! Greet

    howdyGreeter ! WhoToGreet("Lightbend")
    howdyGreeter ! Greet

    helloGreeter ! WhoToGreet("Scala")
    helloGreeter ! Greet

    goodDayGreeter ! WhoToGreet("Play")
    goodDayGreeter ! Greet

    println(">>> Press ENTER to exit <<<")
    StdIn.readLine()
  } finally {
    system.terminate()
  }
}