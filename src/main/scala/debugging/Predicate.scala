package debugging

import scala.annotation.tailrec

object Predicate {

  def main(args: Array[String]): Unit = {
    def test1(s: String): TestResult =
      if (s.contains("42")) Failed
      else Passed

    def test2(s: String): TestResult =
      if (s.contains("4") && s.contains("3")) Failed
      else Passed

    val res = ddmin("154213", test2)
    println(res)
  }

  def ddmin(s: String, test: String => TestResult): String = {
    @tailrec
    def ddmin2(s: String, n: Int, deltasAndCompliments: List[(String, String)]): String = {
      deltasAndCompliments.headOption match {
        case Some((delta, compliment)) =>
          if (test(delta) == Failed) {
            ddmin2(delta, 2, getDeltasAndCompliments(delta, 2))
          } else if (test(compliment) == Failed) {
            val newN = math.max(n - 1, 2)
            ddmin2(compliment, newN, getDeltasAndCompliments(compliment, newN))
          } else if (n < s.length) {
            val newN = math.min(s.length, 2 * n)
            ddmin2(s, newN, getDeltasAndCompliments(s, newN))
          } else {
            ddmin2(s, n, deltasAndCompliments.tail)
          }
        case None => s
      }
    }

    // TODO: generalize
    // c, implicit Decomposable
    // cfSize is how many atomic δ cf has, Decomposable know problem size
    // return List[(deltas: List[Decompose Type], compliments: List[Decompose Type])]
    // now return apply deltas and compliments to cp
    def getDeltasAndCompliments(cf: String, n: Int): List[(String, String)] = {
      val cfSize = cf.length
      val step = cfSize / n
      (step until cfSize by step).map { splitPoint =>
        val cmp1 = cf.take(splitPoint - step)
        val delta = cf.substring(splitPoint - step, splitPoint)
        val cmp2 = cf.drop(splitPoint)
        (delta, cmp1 + cmp2)
      }.toList
    }

    ddmin2(s, 2, getDeltasAndCompliments(s, 2))
  }

  def test(s: String): TestResult =
    if (s.contains("4") && s.contains("2")) Failed
    else Passed

  sealed trait TestResult

  case object Passed extends TestResult

  case object Failed extends TestResult

  case object Unresolved extends TestResult

}
