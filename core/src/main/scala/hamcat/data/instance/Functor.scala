package hamcat.data.instance

import hamcat.data.{ Functor, Id, Const }

/** Instances of functor */
trait FunctorInstances {

  /** Option functor */
  implicit val OptionFunctor: Functor[Option] = new Functor[Option] {
    def fmap[A, B](f: A => B): Option[A] => Option[B] = _.map(f)
  }

  /** List functor */
  implicit val ListFunctor: Functor[List] = new Functor[List] {
    def fmap[A, B](f: A => B): List[A] => List[B] = _.map(f)
  }

  /** Reader functor */
  implicit def Function1Functor[R]: Functor[Function1[R, ?]] = new Functor[Function1[R, ?]] {
    def fmap[A, B](f: A => B): (R => A) => (R => B) = fa =>
      f compose fa
  }

  /** Identity functor */
  implicit val IdentityFunctor: Functor[Id] = new Functor[Id] {
    def fmap[A, B](f: A => B): Id[A] => Id[B] = f(_)
  }

  /** Const functor */
  implicit def ConstFunctor[C]: Functor[Const[C, ?]] = new Functor[Const[C, ?]] {
    def fmap[A, B](f: A => B): Const[C, A] => Const[C, B] = fa =>
      Const(fa.v)
  }
}
