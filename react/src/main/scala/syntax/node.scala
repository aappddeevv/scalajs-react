// Copyright (c) 2018 The Trapelo Group LLC
// This software is licensed under the MIT License (MIT).
// For more information see LICENSE or https://opensource.org/licenses/MIT

package react

import scala.scalajs.js
import js._

/** Convert any scala object to a ReactNode via `blah.toNode`. */
final case class ValueOps[T <: scala.Any](v: T) extends AnyVal {
  def toNode: ReactNode = v.asInstanceOf[ReactNode]
}

/** Enable `value.toNode` syntax. `ValueConverters` enable direct conversion to
 * ReactNode if you want to avoid the syntax and have magic.
 */
trait ValueSyntax {
  implicit def stringValueOpsSyntax(v: String) = ValueOps[String](v)
  implicit def intValueOpsSyntax(v: Int) = ValueOps[Int](v)
  implicit def floatValueOpsSyntax(v: Float) = ValueOps[Float](v)
  implicit def doubleValueOpsSyntax(v: Double) = ValueOps[Double](v)
  implicit def booleanValueOpsSyntax(v: Boolean) = ValueOps[Boolean](v)
}
