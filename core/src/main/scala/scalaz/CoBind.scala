package scalaz

import java.util.Map.Entry

trait CoBind[F[_]] {
  def coBind[A, B](f: F[A] => B): F[A] => F[B]

  def deriving[G[_]](implicit n: ^**^[G, F]): CoBind[G] =
    new CoBind[G] {
      def coBind[A, B](f: G[A] => B) =
        h => n.pack(CoBind.this.coBind((i: F[A]) => f(n.pack(i)))(n.unpack(h)))
    }
}

object CoBind extends CoBinds

trait CoBinds {
  def coBind[F[_]](implicit e: Extend[F]): CoBind[F] = new CoBind[F] {
    def coBind[A, B](f: F[A] => B) =
      e.functor.fmap(f) compose e.coJoin.coJoin
  }

  implicit def MapEntryCoBind[X]: CoBind[({type λ[α] = Entry[X, α]})#λ] =
    coBind[({type λ[α] = Entry[X, α]})#λ]
}