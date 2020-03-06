/**
 * # Cows and bulls
 * ---
 * 1. Computer generates a random 4 digit number: eg 2574
 * 2. User enters a 4 digit number: 4582
 * 3. For each digit matching in the same position: Cow.
 * 4. Digit matching in different position: Bull
 * > Cow: 1, Bull: 2
 *
 * Scala, Pure FP
 */

import cats.effect.ExitCode
import monix.eval.{Task, TaskApp}
import monix.execution.Ack
import monix.reactive.observers.Subscriber
import monix.reactive.{Observable, OverflowStrategy}

import scala.io.StdIn.readLine

object Main extends TaskApp {

  def producerLoop(subscriber: Subscriber[String]): Task[Unit] = {
    Task.deferFuture(subscriber.onNext(readLine())).flatMap {
      case Ack.Continue => producerLoop(subscriber)
      case Ack.Stop => Task.unit
    }
  }

  def run(args: List[String]): Task[ExitCode] = {

    val source = Observable.create[String](OverflowStrategy.Unbounded) { subscriber =>
      producerLoop(subscriber)
      .runToFuture(subscriber.scheduler)
    }.scan{
      println("Welcome to Cows and Bulls.")
      println("Enter a 4 digit number to begin. Find 4 Cows to win!")
      Generator.generate()
    }{(goal, input) => {
      Response.parse(input, goal) match {
        case Response(4, 0) =>
          println("You've won! We've generated a new number for you. Start guessing again.")
          Generator.generate()
        case response =>
          println(response)
          println(goal)
          goal
      }
    }}

    source.completedL.map(_ => ExitCode.Success)
  }
}

