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

object Grid {
  import ttg.react.elements.wrapJsForScala

  @js.native
  @JSImport("@material-ui/core/Grid", JSImport.Default)
  object JS extends ReactJsComponent

  def apply(props: Props = null)(children: ReactNode*) =
    wrapJsForScala(JS, props, children:_*)

  @js.native
  sealed trait AlignContent extends js.Any
  object AlignContent extends Specs2[AlignContent]

  @js.native
  sealed trait AlignItems extends js.Any
  object AlignItems extends Specs2[AlignItems]

  @js.native
  sealed trait Direction extends js.Any
  object Direction {
    val row = "row".asInstanceOf[Direction]
    val rowReverse = "row-reverse".asInstanceOf[Direction]
    val column = "column".asInstanceOf[Direction]
    val columnReverse = "column-reverse".asInstanceOf[Direction]
  }

  trait Specs2[T] {
    val center = "center".asInstanceOf[T]
    val spaceBetween = "space-between"
    val flexStart = "flex-start".asInstanceOf[T]
    val flexEnd = "flex-end".asInstanceOf[T]
    val spaceAround = "space-arouud".asInstanceOf[T]
    val spaceEvenly = "space-evenly".asInstanceOf[T]
  }

  @js.native
  sealed trait Justify extends js.Any
  object Justify extends Specs2[Justify]

  @js.native
  sealed trait Lg extends js.Any
  object Lg extends Specs[Lg]

  @js.native
  sealed trait Md extends js.Any
  object Md extends Specs[Md]

  @js.native
  sealed trait Sm extends js.Any
  object Sm extends Specs[Sm]

  @js.native
  sealed trait Spacing extends js.Any
  object Spacing {
    val _0  = "0".asInstanceOf[Spacing]
    val _24 = "24".asInstanceOf[Spacing]
    val _16 = "16".asInstanceOf[Spacing]
    val _32 = "32".asInstanceOf[Spacing]
    val _8 = "8".asInstanceOf[Spacing]
    val _40 = "40".asInstanceOf[Spacing]
  }

  @js.native
  sealed trait Wrap extends js.Any
  object Wrap {
    val nowrap = "nowrap".asInstanceOf[Wrap]
    val wrap = "wrap".asInstanceOf[Wrap]
    val wrapReverse = "wrap-reverse".asInstanceOf[Wrap]
  }

  trait Specs[T] {
    val _auto = "auto".asInstanceOf[T]
    val _1 = "1".asInstanceOf[T]
    val _2 = "2".asInstanceOf[T]    
    val _3 = "3".asInstanceOf[T]
    val _4 = "4".asInstanceOf[T]
    val _5 = "5".asInstanceOf[T]    
    val _6 = "6".asInstanceOf[T]    
    val _7 = "7".asInstanceOf[T]
    val _8 = "8".asInstanceOf[T]    
    val _9 = "9".asInstanceOf[T]
    val _10 = "10".asInstanceOf[T]
    val _11 = "11".asInstanceOf[T]    
    val _12 = "11".asInstanceOf[T]
    val _true = "true".asInstanceOf[T]
    val _false = "false".asInstanceOf[T]
  }

  @js.native
  sealed trait Xl extends js.Any
  object Xl extends Specs[Xl]

  @js.native
  sealed trait Xs extends js.Any
  object Xs extends Specs[Xs]
          
  trait Props extends js.Object {
    var alignContent: js.UndefOr[AlignContent] = js.undefined
    var alignItems: js.UndefOr[AlignItems] = js.undefined
    var className: js.UndefOr[String] = js.undefined
    var classes: js.UndefOr[js.Object] = js.undefined
    var component: js.UndefOr[js.Any] = js.undefined
    var container: js.UndefOr[Boolean] = js.undefined
    var direction: js.UndefOr[Direction] = js.undefined
    var item: js.UndefOr[Boolean] = js.undefined
    var justify: js.UndefOr[Justify] = js.undefined
    var key: js.UndefOr[String] = js.undefined
    var lg: js.UndefOr[Lg] = js.undefined
    var md: js.UndefOr[Md] = js.undefined
    var sm: js.UndefOr[Sm] = js.undefined
    var spacing: js.UndefOr[String] = js.undefined
    var style: js.UndefOr[js.Object] = js.undefined
    var wrap: js.UndefOr[Wrap] = js.undefined
    var xl: js.UndefOr[Xl] = js.undefined
    var xs: js.UndefOr[Xs] = js.undefined
    var zeroMinWidth: js.UndefOr[Boolean] = js.undefined
  }

}