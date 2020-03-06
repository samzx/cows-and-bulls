import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ResponseSpec extends AnyFunSpec with Matchers {
  describe("parser") {
    it("Parses correctly") {
      val goal = "4567"
      Response.parse("0000", goal) shouldBe Response(0, 0)
      Response.parse("4444", goal) shouldBe Response(1, 0)
      Response.parse("4544", goal) shouldBe Response(2, 0)
      Response.parse("4564", goal) shouldBe Response(3, 0)
      Response.parse("4567", goal) shouldBe Response(4, 0)
      Response.parse("4582", "2574") shouldBe Response(1, 2)
    }
    it("Handles less inputs") {
      val input = "5"
      val goal = "5555"
      Response.parse(input, goal) shouldBe Response(1, 3)
    }

    it("Handles more inputs") {
      val input = "54555555"
      val goal = "4444"
      Response.parse(input, goal) shouldBe Response(1, 3)
    }
  }
}
