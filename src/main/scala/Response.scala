case class Response(cows: Int, bulls: Int) {
  override def toString: String = {
    s"Cow: $cows, Bull: $bulls"
  }
}

object Response {
  def parse(input: String, goal: String): Response = {
    input.zip(goal).map {
      case (inputChar, goalChar) if inputChar == goalChar => Cow
      case _ => Bull
    }.foldLeft(Response(0,0))((acc, next) => {
      next match {
        case Cow => acc.copy(cows = acc.cows + 1)
        case Bull => acc.copy(bulls = acc.bulls + 1)
      }
    })
  }
}
