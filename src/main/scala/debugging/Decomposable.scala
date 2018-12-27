//package debugging
//
//trait Decomposable[Delta] {
//  def decompose(change: Delta, n: Int): List[Delta]
//}
//
//object Decomposable {
//  implicit val appendCharToStringDecomposable: Decomposable[String => String] = new Decomposable[String => String] {
//    // 首先造一个输入输入String，然后把String split开n份变成Delta的函数
//    // 然后对这个函数应用change的argument
//    override def decompose(change: String => String, n: Int): List[String => String] = change match {
//      // 怎么拿到change的参数，拿不到，change都还没有确定
////      case (s => s1: Function1[String, String]) =>
//    }
//  }
//}
//
//// 给一个Subject和一个delta，怎么得到delta(subject)
//// Change就是Subject => Subject
//abstract case class Delta[Subject, A]() { self =>
//
//  def apply(subject: Subject): Subject
//
//  def compose(inner: Delta[Subject, A]): Delta[Subject, A]
//
//  def decompose(n: Int): List[Delta[Subject, A]]
//}
//
//object Delta {
//  case class AppendToStringDelta(toAppend: String) extends Delta[String, Unit] {
//
//    override def apply(subject: String): String = toAppend.concat(subject)
//
////    override def compose(inner: AppendToStringDelta): AppendToStringDelta =
////      AppendToStringDelta(toAppend.concat(inner.toAppend))
//
//    override def decompose(n: Int): List[Delta[String, Unit]] = ???
//
//    override def compose(inner: Delta[String, Unit]): Delta[String, Unit] = ???
//  }
//
////  implicit val appendToStringChange: Delta[String, String] = new Delta[String, String] {
////    override def apply(subject: String): String = ???
////  }
//}