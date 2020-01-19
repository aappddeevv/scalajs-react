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

package ttg
package examples
package helloworld

import scala.scalajs.js

import js.annotation._

import org.scalajs.dom

import react._
import react.implicits._

import vdom._

import tags._

/** Props for make2 outside the HellowWorld object.  By using js.UndefOr for the
 * defintion, a SFC taking these props can easily interop with the js world.
 */
trait HelloWorldProps extends js.Object {
  var name: js.UndefOr[String] = js.undefined
}

/**
 * Demonstrates interop and general props management.
 */
object HelloWorld {
  val Name = "HelloWorld"

  trait Props extends js.Object {
    val name: Option[String]
  }

  def apply()                       = sfc(new Props { val name = None  })
  def apply(name_ : Option[String]) = sfc(new Props { val name = name_ })

  /** No props data structure, just parameters. */
  val sfc = SFC1[Props] { props =>
    React.useDebugValue(Name)
    div("hello world " + props.name.map(": " + _).getOrElse(""))
  }

  trait Props2 extends js.Object {
    val name: Option[String]
  }

  def withMount(name_ : Option[String] = None) =
    sfcWithMount(new Props2 { val name = name_ })

  val sfcWithMount = SFC1[Props2] { props =>
    React.useEffectMounting { () =>
      println("HelloWorld.makeWithMount: didMount was called!")
    }
    div("hello world " + props.name.map(": " + _).getOrElse(""))
  }

  // Exported to javascript world under th nam <modulename>.HelloWorld
  @JSExportTopLevel("HelloWorld")
  val exported = sfc2.run

  def make2(props: HelloWorldProps) = sfc2(props)

  lazy val sfc2 = SFC1[HelloWorldProps] { props =>
    div("hello world (2) " + props.name.toOption.map(": " + _).getOrElse(""))
  }

  trait Props3 extends js.Object {
    val content: String
  }

  def make3(props: Props3) = sfc3(props)
  def make3(c: String)     = sfc3(new Props3 { val content = c })

  val sfc3 = SFC1[Props3] { props =>
    val hwref = React.useRef[dom.html.Div](null)
    div(new DivProps {
      ref = hwref
    })(props.content)
  }
}
