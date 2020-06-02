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
import js.|
import js.annotation._

/** Extra things not specifically core react but mentioned in the FAQ. */
package object extras {

  /** Create an expensive ref.
   *
   *  @see https://reactjs.org/docs/hooks-faq.html#how-to-create-expensive-objects-lazily
   */
  def useExpensiveRef[T](value: => T) = {
    val instanceRef = useRef[T | Null](null)
    () => {
      val instance = instanceRef.current
      if (instance != null) instance.asInstanceOf[T]
      else {
        val x = value
        instanceRef.current = x
        x
      }
    }
  }

  /** Force a render. */
  def useForceRender() = {
    val (s, update) = useStateStrict[Boolean](true)
    () => update(!_)
  }

  /** Use previous value. */
  def usePreviousValue[T](value: T) = {
    val ref = useRef[T](value)
    useEffect(value.hashCode)(() => ref.current = value)
    ref.current
  }

  /** http://usehooks.com. Use inside your hooks to
   * memo a value so callers don't have to. On first
   * compare, value can be null.
   *
   * @param value Value to potentially memo.
   * @param compare (old,new)=>Boolean
   */
  def useMemoCompare[T](value: T, compare: (T | Null, T) => Boolean) = {
    val previousRef = useRef[T | Null](null)
    val previous = previousRef.current
    val isEqual = compare(previous, value)
    useEffectAlways(() => if (!isEqual) previousRef.current = value)
    if (isEqual) previous else value
  }

}
