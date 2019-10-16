// Copyright (c) 2018 The Trapelo Group LLC
// This software is licensed under the MIT License (MIT).
// For more information see LICENSE or https://opensource.org/licenses/MIT

package ttg
package react
package examples

import scala.scalajs.js
import js.JSConverters._

import org.scalajs.dom
import ttg.react
import react._
import elements._
import react.implicits._
import vdom._
import vdom.tags._
import vdom.styling._

import cats._
import cats.implicits._

import react.router.browser._

object int {
  import scala.util._
  def unapply(s: String): Option[Int] =
    Try(s.toInt) match {
      case Success(i) => Option(i)
      case Failure(t) => None
    }
}

object RouterConfig {

  import ReactionRouter._

  def body(bodyContent: ReactNode): ReactionRouter.Control => ReactNode =
    c =>
  AppBody(new AppBody.Props {
    val nav = Nav(new Nav.Props { var goto = c.navigate(_, router.Redirect.Push)})
    val content = bodyContent
  })

  // all static routes so define all in one place
  def config(n: Int = 0, baseUrl: String) = ReactionConfig(
    prefixPath = Some(baseUrl),
    rules = parts => {
      println(s"Running rules with parts ${parts}, $n")
      parts.drop(n).segments.filterNot(_.isEmpty) match {
      case Seq("readme") =>
        Render(body(Pages.readme(readmetext)))

      case Seq("addresses") =>
        Render(body(Pages.addressPage(addressmanager.fakedata.addressDAO)))

      case Seq("todo") =>
        Render(body(Pages.todoPage()))

      case Seq("helloworld") =>
        Render(body(Pages.helloWorldPage()))

      case Seq("changeredux") =>
        Render(body(Pages.changeReduxStatePage()))

      case Seq("labelandchild") =>
        Render(body(Pages.labelAndChild("Typescript Wrapping Scala.js",
          helloworld.HelloWorld())))

      case Seq("tagtest")=>
        Render(body(Pages.tagTest()))

      case Seq("pressure") =>
        Render(body(Pages.pressurePage()))

      case Seq("graph") =>
        Render(body(Pages.graphPage()))

      case Seq("calendar") =>
        Render(body(Pages.calendarPage()))

      case Seq("bootstrap") =>
        Render(body(Pages.bootstrapPage()))

      case Seq("mui") =>
        Render(body(Pages.materialUIPage()))

      case _ =>
          println(s"Invalid URL: No route for ${parts.pathname} defined: ${parts}")
          Render(_ => "A routing configuration error occurred. Press the back button to return to the previous page.")
      }}
  )
}

object Application {

  val baseUrl =
    dom.document.location.origin.asString + BuildSettings.routePrefix.getOrElse("")
  val nsegments =
    BuildSettings.routePrefix.map(_.split("/").filterNot(_.isEmpty).length).getOrElse(0)

  dom.console.log("baseURL", baseUrl, "nsegments to strip", nsegments)

  val Name = "Application"

  def apply() = sfc

  val sfc = SFC0 {
    div(new DivProps {
      className = "ttg-App"
      style = new StyleAttr {
        display = "flex"
        flexDirection = "column"
        alignItems = "stretch"
        height = "100%"
      }
    })(
      Header(),
      ReactionRouter.Route(RouterConfig.config(
        n = nsegments,
        baseUrl = baseUrl
      ))
    )
  }
}

