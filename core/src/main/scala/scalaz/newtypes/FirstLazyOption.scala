package scalaz
package newtypes

import data._

sealed trait FirstLazyOption[A] {
  val value: LazyOption[A]
}

object FirstLazyOption extends FirstLazyOptions

trait FirstLazyOptions {
  implicit def FirstLazyOptionUnpack[A]: Unpack[FirstLazyOption[A], LazyOption[A]] = new Unpack[FirstLazyOption[A], LazyOption[A]] {
    val unpack = (_: FirstLazyOption[A]).value
  }

  implicit def FirstLazyOptionPack[A]: Pack[FirstLazyOption[A], LazyOption[A]] = new Pack[FirstLazyOption[A], LazyOption[A]] {
    val pack = (b: LazyOption[A]) => new FirstLazyOption[A] {
      val value = b
    }
  }

  implicit def FirstLazyOptionNewtype[A]: Newtype[FirstLazyOption[A], LazyOption[A]] =
    Newtype.newtype

  implicit def LazyOptionFirstLazyOption[A](o: LazyOption[A]): FirstLazyOption[A] =
    implicitly[Pack[FirstLazyOption[A], LazyOption[A]]].pack(o)

  implicit def FirstLazyOptionZero[A]: Zero[FirstLazyOption[A]] =
    Zero.zero(implicitly[Pack[FirstLazyOption[A], LazyOption[A]]].pack(LazyOption.lazyNone))

  implicit def FirstLazyOptionSemigroup[A]: Semigroup[FirstLazyOption[A]] = new Semigroup[FirstLazyOption[A]] {
    def append(a1: FirstLazyOption[A], a2: => FirstLazyOption[A]) =
      implicitly[Pack[FirstLazyOption[A], LazyOption[A]]].pack(a1.value orElse a2.value)
  }

  implicit def FirstLazyOptionMonoid[A]: Monoid[FirstLazyOption[A]] =
    Monoid.monoid

  implicit def FirstLazyOptionShow[A: Show]: Show[FirstLazyOption[A]] =
    implicitly[Show[LazyOption[A]]] contramap ((_: FirstLazyOption[A]).value)

  implicit def FirstLazyOptionEqual[A: Equal]: Equal[FirstLazyOption[A]] =
    implicitly[Equal[LazyOption[A]]] contramap ((_: FirstLazyOption[A]).value)

  implicit def FirstLazyOptionOrder[A: Order]: Order[FirstLazyOption[A]] =
    implicitly[Order[LazyOption[A]]] contramap ((_: FirstLazyOption[A]).value)

}