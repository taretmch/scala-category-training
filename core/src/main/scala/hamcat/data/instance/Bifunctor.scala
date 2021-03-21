package hamcat.data.instance

import hamcat.data.Bifunctor

/** Instances of bifunctor */
trait BifunctorInstances {

  /** Product bifunctor */
  implicit def tuple2Bifunctor = new Bifunctor[Tuple2] {
    def bimap[A, B, C, D](f: A => C)(g: B => D): ((A, B)) => ((C, D)) = {
      case (a, b) => (f(a), g(b))
    }
  }

  /** Coproduct bifunctor */
  implicit def eitherBifunctor = new Bifunctor[Either] {
    def bimap[A, B, C, D](f: A => C)(g: B => D): Either[A, B] => Either[C, D] = {
      case Left(a)  => Left(f(a))
      case Right(b) => Right(g(b))
    }
  }
}
