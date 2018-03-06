// Copyright (c) 2018 The Trapelo Group LLC
// This software is licensed under the MIT License (MIT).
// For more information see LICENSE or https://opensource.org/licenses/MIT

package ttg
package react

// based on commit: reason-react master branch

import collection.mutable
import scala.scalajs.js
import js.|
import js.annotation._
import js.JSConverters._
import js.Dynamic.{literal => lit}
import org.scalajs.dom
import dom.html

object elements {

  /** Mostly untyped React.createElement or replacements e.g. glamor's createElement. */
  //type NativeCreateElement = js.Function3[js.Any|String, js.UndefOr[Nothing], Seq[ReactNode], Unit]

  /** Create a DOM element, which is always a string, lowercase. */
  def createDomElement(
      n: String,
      props: js.Object | js.Dynamic,
      children: ReactElement*): ReactDOMElement = {
    JSReact.createElement(n, props.asInstanceOf[js.Object], children: _*)
  }

  /**
    * Scala side version of React.createElement given a scala-side ComponentSpec.
    * It calls React.createElement. If component is a scala-side wrapper around a
    * js component, create the js component. No children are allowed in this
    * function as they come should through the props. This is called "element"
    * instead of "createElement" to make it shorter to type if you are not using
    * JSX. Do not use this if you not have a scala side component. You do *not*
    * use this to create standad html elements like "div."
    *
    * Since we return an untyped value, the types of the component are not important.
    */
  def element(
      component: Component,
      key: Option[String] = None,
      ref: Option[RefCb] = None): ReactElement = {
    component.jsElementWrapped.toOption match {
      case Some(func) => func(key, ref)
      case _ =>
        val props = js.Dictionary.empty[Any] // not js.Any! why?
        key.foreach(k => props("key") = k)
        ref.foreach(refcb => props("ref") = refcb)
        props("scalaProps") = component.asInstanceOf[js.Any]
        JSReact.createElement(component.reactClassInternal, props)
    }
  }

  /** The long-named version of `element`. */
  def createElement(component: Component, key: Option[String] = None, ref: Option[RefCb] = None) =
    element(component, key, ref)

  /** Convert *anything* to what you assert is a js.Any value. Very dangerous. */
  private[this] def toAny(o: scala.Any): js.Any = o.asInstanceOf[js.Any]

  /** Same comment as `toAny`. Very dangerous */
  private[this] def toDynamic(o: scala.Any): js.Dynamic =
    o.asInstanceOf[js.Dynamic]

  /** Stateless component essentially it just has a render. */
  def statelessComponent(debugNameArg: String) = {
    new StatelessComponentCake {
      class ProxyLike extends super.ProxyLike {
        val displayName: String = debugNameArg
      }
      val proxy = new ProxyLike()
      trait ComponentLike extends super.ComponentLike
      val component = new ComponentLike {
        var debugName          = debugNameArg
        var reactClassInternal = reactCreateClass(proxy)
      }
    }
  }

  /** Stateless, with retained props. */
  def statelessComponentWithRetainedProps[RetainedProps](debugNameArg: String) =
    new StatelessComponentWithRetainedPropsCake {
      type RP = RetainedProps
      class ProxyLike extends super.ProxyLike {
        val displayName: String = debugNameArg
      }
      val proxy = new ProxyLike()
      trait ComponentLike extends super.ComponentLike
      val component = new ComponentLike {
        var debugName          = debugNameArg
        var reactClassInternal = reactCreateClass(proxy)
      }
    }

  /** Stateful. */
  def reducerComponent[TheState, Action](debugNameArg: String) =
    new ReducerComponentCake {
      type S = TheState
      type A = Action
      class ProxyLike extends super.ProxyLike {
        val displayName: String = debugNameArg
      }
      val proxy = new ProxyLike()
      trait ComponentLike extends super.ComponentLike
      val component = new ComponentLike {
        var debugName          = debugNameArg
        var reactClassInternal = reactCreateClass(proxy)
      }
    }

  /**
    * Scala side version of React.createClass. Since this function call creates
    * the underlying react js class, call this once per component then define a
    * "make" function to derive a new element/component instance with your input
    * props and functions to define behavior. The default render method renders
    * "Not Implemented" and the default reducer performs no state update.
    *
    * The function creates the shim/proxy scala "component" which is just a
    * "record" of the client-level API and calls React.createClass with that
    * proxy. The shim/proxy react lifecycle/mangement functions call the
    * scala-side client visible API. Since you customize what "self" is for the
    * scala client side API, this function also defines how to create the API
    * "self" value from the underlying javascript "this" pointer. The only
    * reason that each component needs its own "react class" proxy is because
    * debugName is unique to a component. Having said that, the number of
    * "classes" is not large in an application.
    */
  def reducerComponentWithRetainedProps[TheState, RetainedProps, Action](debugNameArg: String) =
    new KitchenSinkComponentCake {
      type S   = TheState
      type RP  = RetainedProps
      type A   = Action
      type ProxyType = ProxyLike

      class ProxyLike extends super.ProxyLike {
        val displayName: String = debugNameArg
      }
      val proxy = new ProxyType()
      trait ComponentLike extends super.ComponentLike
      val component = new ComponentLike {
        var debugName          = debugNameArg
        var reactClassInternal = reactCreateClass(proxy)
      }
    }

  /** Like nullElement but a Component. Useful for optional children. */
  val nullComponent = statelessComponent("null").component

  /** Clone a ReactElement and add new props. You should not use this if you can avoid it. */
  def cloneElement(element: ReactElement, props: js.Object): ReactElement =
    JSReact.cloneElement(element, props.asInstanceOf[js.Dynamic])

  /**
    * Wrap a js side component for scala side usage. You also need to import the
    * react class using standard scala.js import mechanisms and write a "make"
    * function to create your props from "make" parameters. props can be null
    *  if you do not have any saving an allocation of an empty object.
    */
  def wrapJsForScala[P <: js.Object](
      reactClass: ReactJsComponent,
      props: P,
      children: ReactNode*): Component = {
    WrapProps.wrapJsForScala(reactClass, props, children: _*)
  }

  /** 
   * Create a fragment element. Per the API, you are only allowed an optional key.
   * @deprecated Use `Fragment.make()`.
   */
  def fragmentElement(key: Option[String] = None)(children: ReactNode*) =
    React.createFragment(key, children: _*)

  object Fragment {
    def make(key: Option[String] = None)(children: ReactNode*) =
      React.createFragment(key, children: _*)
  }

  /**
    * Wrap a scala component to be used in js with reactjs. The js props
    * converter is attached to the js side component.  When the converter is
    * called, it should take a js.Object (untyped) and convert it to a
    * component, typically via a call to "make."  jsPropsToScala can use a
    * js.native trait inheriting from js.Object to make picking out the values
    * from the js-side easier, or not, it's up to you. The returned value should
    * be exported from scala-world so that js-world can see it.
    *
    * Note: jsPropsToScala will appear in reactsj's 'this' because its attached
    * to the prototype of reactClassInternal.
    */
  def wrapScalaForJs[P <: js.Object](
      c: Component,
      jsPropsToScala: P => ComponentSpec): ReactJsComponent = {
    require(c != null, "wrapScalaForJs: Component must be non-null.")
    val dyn = c.reactClassInternal.asInstanceOf[js.Dynamic]
    dyn.prototype.jsPropsToScala = jsPropsToScala.asInstanceOf[js.Any]
    c.reactClassInternal.asInstanceOf[ReactJsComponent]
  }
}
