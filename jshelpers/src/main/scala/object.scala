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

package jshelpers

import scala.scalajs.js
import js.|

/** The "combine" methods are shallow, mutable merges, this may not be what you want. */
final case class JsObjectOps[A <: js.Object](o: A) {
  def asDict[B] = o.asInstanceOf[js.Dictionary[B]]
  def asAnyDict = o.asInstanceOf[js.Dictionary[js.Any]]
  def asDyn = o.asInstanceOf[js.Dynamic]
  def asUndefOr: js.UndefOr[A] = js.defined(o)

  /** Shallow merge ! If you are merging multiple levels of props, this is not
   * what you want to use.
   */
  def combine(that: js.UndefOr[A]) = js.Object.assign(o, that.asInstanceOf[js.Object]).asInstanceOf[A]

  /** Combine with a js.Dynamic explicitly. */
  def combineDynamic(that: js.Dynamic) = js.Object.assign(o, that.asInstanceOf[js.Object]).asInstanceOf[A]

  /** Combine with a generic js object or undefined. */
  def combineGeneric(that: js.UndefOr[js.Object]) = js.Object.assign(o, that.asInstanceOf[js.Object]).asInstanceOf[A]

  /** Combine with something! Client takes ownership to make sure `that` is suitable to be combined. */
  def unsafeCombine(that: js.Any) = js.Object.assign(o, that.asInstanceOf[js.Object]).asInstanceOf[A]

  /** Combine with a generic js object and cast. */
  def combineGenericTo[B <: js.Object](that: js.Object) = js.Object.assign(o, that).asInstanceOf[B]

  /** Combine with a dynamic and cast. */
  def combineDynamicTo[B <: js.Object](that: js.Dynamic) =
    js.Object.assign(o, that.asInstanceOf[js.Object]).asInstanceOf[B]

  /** `.asInstanceOf[T]` but shorter. Very dangerous! */
  def as[T <: js.Object] = o.asInstanceOf[T]

  /** Duplicate using `js.Object.assign` */
  def duplicate = js.Object.assign(new js.Object, o).asInstanceOf[A]

  /** Duplicate then combineDynamic */
  def duplicateWithDynamic(that: js.Dynamic) =
    js.Object.assign(new js.Object, o, that.asInstanceOf[js.Object]).asInstanceOf[A]

  /** Duplicate then combineDynamic then cast :-). */
  def duplicateWithDynamicTo[B <: js.Object](that: js.Dynamic) =
    js.Object.assign(new js.Object, o, that.asInstanceOf[js.Object]).asInstanceOf[B]
}

/** Dictionary casts. */
final case class JsDictionaryOps(self: js.Dictionary[_]) {
  def asJsObj = self.asInstanceOf[js.Object]
  def asDyn = self.asInstanceOf[js.Dynamic]
  def asUndefOr = js.defined(self)

  /** `.asInstanceOf[T]` but shorter. Very dangerous! */
  def as[T <: js.Object] = self.asInstanceOf[T]

}

trait JsObjectSyntax {
  implicit def jsObjectOpsSyntax[A <: js.Object](a: A) = new JsObjectOps(a)
  implicit def jsDictionaryOpsSyntax(a: js.Dictionary[_]) = new JsDictionaryOps(a)
}
