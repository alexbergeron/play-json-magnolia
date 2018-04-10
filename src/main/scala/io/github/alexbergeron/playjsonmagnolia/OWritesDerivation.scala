package io.github.alexbergeron.playjsonmagnolia

import language.experimental.macros
import magnolia._
import play.api.libs.json.{JsObject, OWrites}

object OWritesDerivation {
  type Typeclass[T] = OWrites[T]

  def combine[T](ctx: CaseClass[OWrites, T]): OWrites[T] = OWrites[T] {
    value => JsObject(ctx.parameters.map { p =>
      p.label -> p.typeclass.writes(p.dereference(value))
    })
  }

  def dispatch[T](ctx: SealedTrait[OWrites, T]): OWrites[T] =
    OWrites[T] {
      value => ctx.dispatch(value) { sub =>
        sub.typeclass.writes(sub.cast(value))
      }
    }

  implicit def gen[T]: OWrites[T] = macro Magnolia.gen[T]

}
