// Copyright (c) 2019 The Trapelo Group LLC
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

object List {

  @js.native
  @JSImport("office-ui-fabric-react/lib/List", "List")
  object JS extends ReactJsComponent

  def apply[T <: js.Object](props: Props[T] = null)(children: ReactNode*) =
    React.createElement(JS, props)(children: _*)

  trait Props[T <: js.Object] extends IViewportProps with ComponentRef[IList] {

    var getKey
        : js.UndefOr[js.Function2[T, js.UndefOr[Int], String | Int] | js.Function1[T, String | Int]] =
      js.undefined
    val items: js.Array[T]
    var setKey: js.UndefOr[String]           = js.undefined
    var className: js.UndefOr[String]        = js.undefined
    var initialFocusedIndex: js.UndefOr[Int] = js.undefined
    // do the groups thing
    var selection: js.UndefOr[ISelection[T]]                = js.undefined
    var selectionMode: js.UndefOr[SelectionMode]                      = js.undefined
    var selectionPreservedOnEmptyClick: js.UndefOr[Boolean] = js.undefined
    var layoutMode: js.UndefOr[Int]                         = js.undefined
    // checkbox visibility....
    var isHeaderVisible: js.UndefOr[Boolean] = js.undefined
    var columns: js.UndefOr[js.Array[IColumn] | js.Array[js.Object] | js.Array[js.Dynamic]] =
      js.undefined
    var constrainMode: js.UndefOr[Int] = js.undefined

    var onItemInvoked: js.UndefOr[OII[T]] = js.undefined

    var onRenderRow: js.UndefOr[IRenderFunction[
      Details.Row.Props]]              = js.undefined
    var onRenderMissingItem: js.UndefOr[js.Function1[Int, js.Any]]              = js.undefined
    var onRenderDetailsHeader: js.UndefOr[IRenderFunction[Details.Header.Props]] = js.undefined

    /** You can still be "active" but not deselected. */
    var onActiveItemChanged: js.UndefOr[OAIC[T]] = js.undefined
    @JSName("onActiveItemChanged")
    var onActiveItemChanged2: js.UndefOr[js.Function2[T,Int,Unit]] = js.undefined
    @JSName("onActiveItemChanged")
    var onActiveItemChanged1: js.UndefOr[js.Function1[T,Unit]] = js.undefined
    
    var onColumnHeaderClick: js.UndefOr[OCHC[T]] = js.undefined

    var maximumPixelsForDrag: js.UndefOr[Int]           = js.undefined
    var compact: js.UndefOr[Boolean]                    = js.undefined
    var checkboxCellClassName: js.UndefOr[String]       = js.undefined
    var enterModelSelectionOnTouch: js.UndefOr[Boolean] = js.undefined

    var usePageCache: js.UndefOr[Boolean] = js.undefined
    var renderCount: js.UndefOr[Int]      = js.undefined
  }

  type OII[T <: js.Object] = js.Function3[
    T,
    Int,
    SyntheticFocusEvent[dom.html.Element],
    Unit]

  type OAIC[T <: js.Object] = js.Function3[
    T,
    Int,
    SyntheticFocusEvent[dom.html.Element],
    Unit]

  type OCHC[T <: js.Object] =
    js.Function2[SyntheticMouseEvent[dom.html.Element], IColumn, Unit]

  @js.native
  trait IList extends js.Object {
    def forceUpdate(): Unit = js.native
    def scrollToIndex(
      index: Int,
      measureItem: js.UndefOr[js.Function1[Int, Int]] = js.undefined,
      scrollToMode: js.UndefOr[ScrollToMode] = js.undefined): Unit = js.native
    def getTotalListHeight(): Int = js.native
    def getStartItemIndexInView(): Int = js.native
  }

  @js.native
  sealed trait ScrollToMode extends js.Any
  object ScrollToMode {
    var auto = 0.asInstanceOf[ScrollToMode]
    var top = 1.asInstanceOf[ScrollToMode]
    var bottom = 2.asInstanceOf[ScrollToMode]
    var center = 3.asInstanceOf[ScrollToMode]
  }

}


