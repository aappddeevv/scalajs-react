// Copyright (c) 2018 The Trapelo Group LLC
// This software is licensed under the MIT License (MIT).
// For more information see LICENSE or https://opensource.org/licenses/MIT

package fabric
package components

import scala.scalajs.js
import js.annotation._
import js.|
import org.scalajs.dom

import react._
import vdom._
import fabric.styling._

object SearchBox {
  @js.native
  @JSImport("office-ui-fabric-react/lib/SearchBox", "SearchBox")
  object JS extends ReactJsComponent

  def apply(props: Props = null) =
    React.createElement0(JS, props)

  @js.native
  trait ISearchBox extends Focusable {
    def hasFocus(): Boolean = js.native
  }

  trait Styles extends IStyleSetTag {
    var root: js.UndefOr[IStyle] = js.undefined
    var iconContainer: js.UndefOr[IStyle] = js.undefined
    var icon: js.UndefOr[IStyle] = js.undefined
    var field: js.UndefOr[IStyle] = js.undefined
    var clearButton: js.UndefOr[IStyle] = js.undefined
  }

  trait StyleProps extends Attributes {
    //var theme: js.UndefOr[ITheme] = js.undefined
    var className: js.UndefOr[String] = js.undefined
    var disabled: js.UndefOr[Boolean] = js.undefined
    var hasFocus: js.UndefOr[Boolean] = js.undefined
    var underlined: js.UndefOr[Boolean] = js.undefined
    var hasInput: js.UndefOr[Boolean] = js.undefined
    var disableAnimation: js.UndefOr[Boolean] = js.undefined
  }

  trait Props extends InputHTMLAttributes[dom.html.Input] with Attributes {
    var ariaLabel: js.UndefOr[String] = js.undefined
    //var className: js.UndefOr[String] = js.undefined
    var clearButtonProps: js.UndefOr[Button.Props] = js.undefined
    var disableAnimation: js.UndefOr[Boolean] = js.undefined
    //var placeholder: js.UndefOr[String] = js.undefined
    var underlined: js.UndefOr[Boolean] = js.undefined
    //var value: js.UndefOr[String]                       = js.undefined

    var styles: js.UndefOr[IStyleFunction[StyleProps, Styles]] = js.undefined

    // onChange?: (event?: React.ChangeEvent<HTMLInputElement>, newValue?: string) => void;
    /** new value, as you type, already covered in superclass */
    @JSName("onChange")
    var onChangeValue: js.UndefOr[js.Function2[SyntheticChangeEvent[dom.html.Input], String, Unit]] = js.undefined
    /** Event returned. */
    var onClear: js.UndefOr[js.Function1[js.Any, Unit]] = js.undefined
    /** Event returned. */
    var onEscape: js.UndefOr[js.Function1[js.Any, Unit]] = js.undefined
    /** For onChange & onSearch, return the string value or "" if no value.  You can
     * use implicit `.asString.toTruthyUndefOr.toOption` to set "" => None.
     * Typescript does not set this as a string but js.Any...not sure why.
     */
    var onSearch: js.UndefOr[js.Function1[js.Any, Unit]] = js.undefined
  }

}
