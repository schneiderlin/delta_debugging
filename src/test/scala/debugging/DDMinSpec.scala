package debugging

import debugging.DDMin._
import org.scalatest.{FlatSpec, Matchers}

class DDMinSpec extends FlatSpec with Matchers {
  it should "contains out only the buggy string" in {
    def test1(s: String): TestResult =
      if (s.contains("42")) Failed
      else Passed

    def test2(s: String): TestResult =
      if (s.contains("4") && s.contains("3")) Failed
      else Passed

    val res1 = ddmin("154213", test1)
    val res2 = ddmin("154213", test2)

    res1 shouldEqual "42"
    res2 shouldEqual "43"
  }
}
