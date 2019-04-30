// Copyright (c) 2018 The Trapelo Group LLC
// This software is licensed under the MIT License (MIT).
// For more information see LICENSE or https://opensource.org/licenses/MIT

package ttg.react
package mui
package components

import scala.scalajs.js
import js.annotation._
import js.|
import org.scalajs.dom._

import vdom._

object Tab {
  import ttg.react.elements.wrapJsForScala

  @js.native
  @JSImport("@material-ui/core/Tab", JSImport.Default)
  object JS extends ReactJsComponent

  def apply(props: Props = null) =
    wrapJsForScala(JS, props)

  @js.native
  sealed trait TextColor extends js.Any
  object TextColor {
    val secondary = "secondary".asInstanceOf[TextColor]
    val primary= "primary".asInstanceOf[TextColor]
    val inherit = "inherit".asInstanceOf[TextColor]
  }
          
  trait Props extends js.Object {
    var TouchRippleProps: js.UndefOr[js.Object] = js.undefined
    var action: js.UndefOr[js.Any] = js.undefined
    var buttonRef: js.UndefOr[js.Any] = js.undefined
    var centerRipple: js.UndefOr[Boolean] = js.undefined
    var className: js.UndefOr[String] = js.undefined
    var classes: js.UndefOr[js.Object] = js.undefined
    var component: js.UndefOr[js.Any] = js.undefined
    var disableRipple: js.UndefOr[Boolean] = js.undefined
    var disableTouchRipple: js.UndefOr[Boolean] = js.undefined
    var disabled: js.UndefOr[Boolean] = js.undefined
    var focusRipple: js.UndefOr[Boolean] = js.undefined
    var focusVisibleClassName: js.UndefOr[String] = js.undefined
    var fullWidth: js.UndefOr[Boolean] = js.undefined
    var icon: js.UndefOr[ReactNode] = js.undefined
    var indicator: js.UndefOr[ReactNode] = js.undefined
    var key: js.UndefOr[String] = js.undefined
    var label: js.UndefOr[ReactNode] = js.undefined
    var onBlur: js.UndefOr[FocusEventHandler[html.Input]] = js.undefined
    var onChange: js.UndefOr[ReactEventHandler[html.Input]] = js.undefined
    var onClick: js.UndefOr[MouseEventHandler[html.Input]] = js.undefined
    var onFocus: js.UndefOr[FocusEventHandler[html.Input]] = js.undefined
    var onFocusVisible: js.UndefOr[scalajs.js.Function0[Unit]] = js.undefined
    var onKeyDown: js.UndefOr[KeyboardEventHandler[html.Input]] = js.undefined
    var onKeyUp: js.UndefOr[KeyboardEventHandler[html.Input]] = js.undefined
    var onMouseDown: js.UndefOr[ReactMouseEvent[html.Input]] = js.undefined
    var onMouseLeave: js.UndefOr[ReactMouseEvent[html.Input]] = js.undefined
    var onMouseUp: js.UndefOr[ReactMouseEvent[html.Input]] = js.undefined
    var onTouchEnd: js.UndefOr[ReactTouchEvent[html.Input]] = js.undefined
    var onTouchMove: js.UndefOr[ReactTouchEvent[html.Input]] = js.undefined
    var onTouchStart: js.UndefOr[ReactTouchEvent[html.Input]] = js.undefined
    var role: js.UndefOr[String] = js.undefined
    var selected: js.UndefOr[Boolean] = js.undefined
    var style: js.UndefOr[js.Object] = js.undefined
    var tabIndex: js.UndefOr[js.Any] = js.undefined
    var textColor: js.UndefOr[TextColor] = js.undefined
    var `type`: js.UndefOr[String] = js.undefined
    var value: js.UndefOr[js.Any] = js.undefined
  }
}