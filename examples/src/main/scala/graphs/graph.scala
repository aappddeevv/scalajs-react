// Copyright (c) 2018 The Trapelo Group LLC
// This software is licensed under the MIT License (MIT).
// For more information see LICENSE or https://opensource.org/licenses/MIT

package ttg.react.examples
package graphs

import scala.scalajs.js
import js.Dynamic.{literal => jsobj}
import org.scalajs.dom
import ttg._
import react._
import vdom._
import tags._
import elements._
import implicits._

import fabric._
import fabric.components._

import cytoscape._

// object GraphPage {
//   val c = statelessComponent("GraphPage")
//   import c.ops._

//   render { self =>
//     div(new DivProps {})(
//       CommandBar(new ICommandBarProps {
//         val items = js.Array()
//       })(),
//       Graph.make()
//     )
//   }
// }

/**
  * Treat the graph and ref as instance vars in State since State in
  * reason-react is really just instance vars and the component definition is
  * really a closure around that so you should feel free to mutate it.
  */
object Graph {
  sealed trait Action

  /** Use Box? Probably does not matter much given the way that Graph is created
    * given an element.
   */
  case class State(
      var ref: Option[dom.html.Div] = None,
      var cy: Option[Graph] = None,
  )

  Cytoscape.use(Dagre)
  Cytoscape.use(Cola)
  //Cytoscape.use(CoseBilkent)
  //Cytoscape.use(Arbor)
  //Cytoscape.use(Spread)
  //Cytoscape.use(Klay)

  val coseLayout = jsobj(
    "name"    -> "cose",
    "animate" -> true,
    "fit"     -> false,
  )

  val colaLayout = jsobj(
    "name"     -> "cola",
    "animate"  -> true,
    "infinite" -> true,
    "fit"      -> false,
  )

  val dagreLayout = jsobj(
    "name" -> "dagre"
  )

  val coseBilkentLayout = jsobj(
    "name"              -> "cose-bilkent",
    "animate"           -> "end",
    "animationEasing"   -> "ease-out",
    "animationDuration" -> 1000,
    "randomize"         -> true
  )

  val Name = "Graph"
  val c = reducerComponent[State, Action](Name)
  import c.ops._

  def destroy(s: State) = s.cy.foreach(_.destroy())

  def build(s: State) =
    s.cy = s.ref.flatMap(g => Option(mkGraph(g)))

  def mkGraph(el: dom.html.Element) =
    Cytoscape(new CytoscapeOptions {
      boxSelectionEnabled = false
      autounselectify = true
      container = el
      layout = colaLayout
      style = js.Array(
        jsobj("selector" -> "node",
              "css" -> jsobj(
                "content"     -> js.Any.fromFunction1((e: Element) => e.data("id")),
                "text-valign" -> "center",
                "text-halign" -> "center"
              )),
        jsobj(
          "selector" -> "$node > node",
          "css" -> jsobj(
            "padding-top"      -> "10px",
            "padding-left"     -> "10px",
            "padding-bottom"   -> "10px",
            "padding-right"    -> "10px",
            "text-valign"      -> "top",
            "text-halign"      -> "center",
            "background-color" -> "#bbb"
          )
        ),
        jsobj(
          "selector" -> "edge",
          "css"      -> jsobj("target-arrow-shape" -> "triangle")
        ),
        jsobj(
          "selector" -> ":selected",
          "css" -> jsobj(
            "background-color"   -> "black",
            "line-color"         -> "black",
            "target-arrow-color" -> "black",
            "source-arrow-color" -> "black"
          )
        )
      )
      elements = jsobj(
        "nodes" -> js.Array(
          jsobj("data"     -> jsobj("id" -> "a", "parent" -> "b"),
                "position" -> jsobj("x"  -> 215, "y" -> 85)),
          jsobj("data"     -> jsobj("id" -> "b")),
          jsobj("data"     -> jsobj("id" -> "c", "parent" -> "b"),
                "position" -> jsobj("x"  -> 300, "y" -> 85)),
          jsobj("data"     -> jsobj("id" -> "d")),
          jsobj("data"     -> jsobj("id" -> "e")),
          jsobj("data"     -> jsobj("id" -> "f", "parent" -> "e"),
                "position" -> jsobj("x"  -> 300, "y" -> 175)),
        ),
        "edges" -> js.Array(
          jsobj("data" -> jsobj("id" -> "ad", "source" -> "a", "target" -> "d")),
          jsobj("data" -> jsobj("id" -> "eb", "source" -> "e", "target" -> "b")),
        )
      )
    })

  def apply() =
    c.copy(new methods {
      val initialState = _ => {
        State()
      }

      didMount = js.defined { self =>
        println(s"$Name: didMount, building graph")
        build(self.state)
      }

      willUnmount = js.defined { self =>
        println(s"$Name: willUnmount")
        destroy(self.state)
      }

      didUpdate = js.defined { onSelf =>
        println(s"$Name: didUpdate")
        destroy(onSelf.newSelf.state)
        build(onSelf.newSelf.state)
        onSelf.newSelf.state.cy.foreach(dom.console.log(_))
      }

      val reducer = (action, state, gen) => {
        println(s"$Name: reducer")
        action match {
          // any other messages?
          case _ => gen.skip
        }
      }

      val render = self => {
        div(new DivProps {
          id = "cy"
          style = new StyleAttr {
            minWidth = "300px"
            minHeight = "500px"
            height = "100%"
            width = "100%"
            display = "block"
          }
          ref = refCallback[dom.html.Div](el => self.state.ref = el.toNonNullOption)
        })()
      }
    })

}