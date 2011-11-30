package scalaz.example

import scalaz._, Scalaz._
import iteratee._, Iteratee._

object IterateeUsage {

  head[Unit, Int, Id]

//    (head[Unit, Int, Id] >>== Stream(1, 2, 3)).apply(_ => none) assert_=== some(1)

}