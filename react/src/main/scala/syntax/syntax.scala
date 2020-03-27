/*
 * Copyright (c) 2018 The Trapelo Group
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package react

import scala.scalajs.js

import js._

/**
  * A data type commonly found when working with dropdown/labels.
  */
final case class StringIntMiscOrSyntax(a: String | Int) {
  @inline def asInt: Int = a.asInstanceOf[Int]
  @inline def asString: String = a.asInstanceOf[String]
}

trait MiscOrSyntax {
  @inline implicit def stringIntMiscOrSyntax(a: String | Int) = StringIntMiscOrSyntax(a)
}

final case class JsRefOps[T](v: ReactRef[T]) {
  @inline def toDyn: js.Dynamic = refToJs[T](v)
}

trait JsRefSyntax {
  implicit def refToJsRefOps[T](v: ReactRef[T]) = JsRefOps(v)
}

// order matters here based on implicit search through hierarchy
trait AllSyntax
    extends ComponentSyntax
    with ValueSyntax
    with JsDynamicSyntax
    with JsObjectSyntax
    with JsAnySyntax
    with OrNullSyntax
    with ScalaMappedSyntax
    with OptionSyntax
    with JsUndefOrSyntax
    with MiscOrSyntax
    with JsRefSyntax
    with JSPromiseSyntax
    with ContextSyntax
    with CallbackSyntax
    with OrSyntax

object syntax {
  object all extends AllSyntax
  object component extends ComponentSyntax
  object value extends ValueSyntax
  object jsdynamic extends JsDynamicSyntax
  object jsundefor extends JsUndefOrSyntax
  object jsobject extends JsObjectSyntax
  object jsany extends JsAnySyntax
  object scalaany extends ScalaMappedSyntax
  object ornull extends OrNullSyntax
  object option extends OptionSyntax
  object miscor extends MiscOrSyntax
  object jsref extends JsRefSyntax
  object jspromise extends JSPromiseSyntax
  object context extends ContextSyntax
  object callback extends CallbackSyntax
  object or extends OrSyntax
}
