case class Response(cows: Int, bulls: Int) {
  override def toString: String = {
    s"Cow: $cows, Bull: $bulls"
  }
}

object Response {
  def parse(input: String, goal: String): Response = {
    goal.toList.zipWithIndex.foldLeft(Response(0,0)){ (accGoal, nextGoal) =>
      input.toList.zipWithIndex.foldLeft[Option[Animal]](None){ (accInput: Option[Animal], nextInput) =>
        nextInput match {
          case (nextGoal._1, nextGoal._2) => Some(Cow)
          case (nextGoal._1, _) if !accInput.contains(Cow) => Some(Bull)
          case _ => accInput
        }
      } match {
        case Some(Cow) => accGoal.copy(cows = accGoal.cows + 1)
        case Some(Bull) => accGoal.copy(bulls = accGoal.bulls + 1)
        case None =>accGoal
      }
    }
  }
}
