import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class GeneratorSpec extends AnyFunSpec with Matchers {
  describe("generator") {
    // Use PBT instead.
    it("Generates 4 digits") {
      (1 to 1000).map( _ => Generator.generate(4)).reduce(_ + _).length shouldBe 4000
    }
  }
}
