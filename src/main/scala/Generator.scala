import scala.util.Random

object Generator {
  private val rnd = new Random
  private val MAX_INT = 10

  def generate(): String = {
    (0 to 3).toList.foldLeft("")((acc, _) => acc ++ rnd.nextInt(MAX_INT).toString)
  }
}
