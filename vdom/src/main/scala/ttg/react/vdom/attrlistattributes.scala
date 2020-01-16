// Copyright (c) 2019 The Trapelo Group LLC
// This software is licensed under the MIT License (MIT).
// For more information see LICENSE or https://opensource.org/licenses/MIT

package react.vdom

import attrlist._

/** Incomplete attribute list. Define your own if you need to.
 */
trait HtmlAttrs {
  final lazy val className     = attr("className")
  final lazy val href          = attr("href")
  final lazy val ref           = attr("ref")
  final lazy val key           = attr("key")
  final lazy val action        = attr("action")
  final lazy val method        = attr("method")
  final lazy val id            = attr("id")
  final lazy val target        = attr("target")
  final lazy val name          = attr("name")
  final lazy val alt           = attr("alt")
  final lazy val onBlur        = attr("onBlur")
  final lazy val onChange      = attr("onChange")
  final lazy val onClick       = attr("onClick")
  final lazy val onDblClick    = attr("onDoubleClick")
  final def onDoubleClick      = onDblClick
  final lazy val onError       = attr("onError")
  final lazy val onFocus       = attr("onFocus")
  final lazy val onKeyDown     = attr("onKeyDown")
  final lazy val onKeyUp       = attr("onKeyUp")
  final lazy val onKeyPress    = attr("onKeyPress")
  final lazy val onLoad        = attr("onLoad")
  final lazy val onMouseDown   = attr("onMouseDown")
  final lazy val onMouseEnter  = attr("onMouseEnter")
  final lazy val onMouseLeave  = attr("onMouseLeave")
  final lazy val onMouseMove   = attr("onMouseMove")
  final lazy val onMouseOut    = attr("onMouseOut")
  final lazy val onMouseOver   = attr("onMouseOver")
  final lazy val onMouseUp     = attr("onMouseUp")
  final lazy val onTouchCancel = attr("onTouchCancel")
  final lazy val onTouchEnd    = attr("onTouchEnd")
  final lazy val onTouchMove   = attr("onTouchMove")
  final lazy val onTouchStart  = attr("onTouchStart")
  final lazy val onSelect      = attr("onSelect")
  final lazy val onScroll      = attr("onScroll")
  final lazy val onSubmit      = attr("onSubmit")
  final lazy val onReset       = attr("onReset")
  final lazy val rel           = attr("rel")
  final lazy val src           = attr("src")
  final lazy val style         = attr("style")
  final lazy val title         = attr("title")
  final lazy val `type`        = attr("type")
  final lazy val xmlns         = attr("xmlns")
  final lazy val lang          = attr("lang")
  final lazy val placeholder   = attr("placeholder")
  final lazy val spellCheck    = attr("spellCheck")
  final lazy val value         = attr("value")
  final lazy val accept        = attr("accept")
  final lazy val autoComplete  = attr("autoComplete")
  final lazy val autoFocus     = attr("autoFocus")
  final lazy val checked       = attr("checked")
  final lazy val charset       = attr("charset")
  final lazy val disabled      = attr("disabled")
  final lazy val `for`         = attr("htmlFor")
  final lazy val readOnly      = attr("readOnly")
  final lazy val required      = attr("required")
  final lazy val rows          = attr("rows")
  final lazy val cols          = attr("cols")
  final lazy val size          = attr("size")
  final lazy val tabIndex      = attr("tabIndex")
  final lazy val role          = attr("role")
  final lazy val contentAttr   = attr("content")
  final lazy val httpEquiv     = attr("httpEquiv")
  final lazy val media         = attr("media")
  final lazy val colSpan       = attr("colSpan")
  final lazy val rowSpan       = attr("rowSpan")
}
