import scala.util.Random

object Generator {
  private val rnd = new Random
  private val MAX_INT = 10

  def generate(digits: Int): String = {
    (1 to digits).toList.foldLeft("")((acc, _) => acc ++ rnd.nextInt(MAX_INT).toString)
  }
}
