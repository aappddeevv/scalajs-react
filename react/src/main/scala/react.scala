// Copyright (c) 2018 The Trapelo Group LLC
// This software is licensed under the MIT License (MIT).
// For more information see LICENSE or https://opensource.org/licenses/MIT

package react

import scala.scalajs.js
import js.annotation._
import js.|
import js._
import js.JSConverters._
import js.Dynamic.{literal => lit}

import org.scalajs.dom

/**
 * Scala-side access to JSReact APIs. Some of these methods overlay the raw JS
 * version to provide the convenience of using scala types as inputs and saving
 * a few conversion nits.
 * 
 * Some of the *N() hook functions like useCallback may be quite inefficient in
 * their conversion from scala functions to js functions and back. Perhaps we
 * need another parallel API function set with the js functions being assumed
 * for those code bases that already have the js versions. Note that useCallback
 * returns a js function so it can be used as a dependency.
 */
trait React {

  /** Clone a ReactElement and add new props. You should not use this if you can avoid it. */
  def cloneElement(element: ReactElement, props: js.Object): ReactElement =
    ReactJS.cloneElement(element, props.asInstanceOf[js.Dynamic])

  /**
    * Given a js.Object (or subclass), find a "children" property and assume its
    * an array of ReactNodes. If not found, return an empty js array. This
    * function is used for interop where your scala "make" method takes children
    * as a separate parameter but your props (event JS friendly props) does not
    * contain the children property--it's there if there are children on the js
    * side. This is not needed in scalajs-react because the children are passed
    * and managed explicitly.
    *
    * @todo Seems like this is an expensive call. Can we do better?
    */
  @inline def extractChildren(item: js.UndefOr[js.Object]): js.Array[ReactNode] =
    if (item == null) js.Array() // need this since could be a "defined" null
    else
      item.toOption
        .flatMap(_.asInstanceOf[js.Dictionary[js.Array[ReactNode]]].get("children"))
        .getOrElse[js.Array[ReactNode]](js.Array())

  /** Create a DOM element via its string name. */
  def createDOMElement(tag: String, props: js.Object|js.Dynamic)(
    children: ReactNode*): ReactDOMElement = 
    ReactJS.createElement(tag, props, children:_*).asInstanceOf[ReactDOMElement]

  /** Create an element with props and children. */
  def createElement(tag: ReactType, props: js.Object)(
      children: ReactNode*): ReactElement =
    ReactJS.createElement(tag, props, children: _*)

  /** Create an element with props and 0 children. */
  def createElement0(tag: ReactType, props: js.Object): ReactElement =
    ReactJS.createElement(tag, props)

  /** Create an elemnt with 1 child only. */
  def createElement1(tag: ReactType, props: js.Object, child: ReactNode): ReactElement =
    ReactJS.createElement(tag, props, child)

  /** Create an element without props but with children. */
  def createElement(tag: ReactType)(
    children: ReactNode*): ReactElement =
    ReactJS.createElement(tag, js.undefined, children: _*)

  /** Create a react fragment. Fragments are created as an "element" with a
    * specific tag (symbol or number if target does not support symbol) vs say,
    * the string "div".
    */
  def createFragment(key: Option[String], children: ReactNode*): ReactElement = {
    val props = js.Dictionary.empty[js.Any]
    key.foreach(props("key") = _)
    ReactJS.createElement(ReactJS.Fragment, props, children: _*)
  }

  /** Create a `ReactRef`. */
  def createRef[T](): react.ReactRef[T]  = ReactJS.createRef[T]()

  /** Memoize a functional component defined in scala. Standard js comparison
   * semantics using Object.is will be used on the props.
   */
  def memo[P <: js.Object](fc: SFC1[P]): SFC1[P] = new SFC1(ReactJS.memo(fc.run))

  /** Memoize a functional component defined in scala; provide a compare function
   * for the props.
   */
  def memo[P <: js.Object](fc: SFC1[P], compare: (P,P) => Boolean): SFC1[P] =
    new SFC1(ReactJS.memo(fc.run, js.Any.fromFunction2[P,P,Boolean](compare)))

  /** Use general P and scala equality comparisons to detect prop changes. */
  def memoScala[P](fc: SFC1[P]): SFC1[P] =
    new SFC1(ReactJS.memo(fc.run, js.Any.fromFunction2[P,P,Boolean]((l,r) => l == r)))

  def useContext[T](context: ReactContext[T]): T = ReactJS.useContext(context)

  /** Initial value is strict. Setter is an updater. Don't use this one. */
  def useStateStrict[T](initial: T) = {
    val c = ReactJS.useState[T](initial.asInstanceOf[js.Any])
    (c._1, c._2.asInstanceOf[js.Function1[js.Function1[T,T], Unit]])
  }

  /** Initial value is a "lazy". Setter is an updater. Use this one or
   * useReducer.
   */
  def useState[T](initial: () => T) = {
    val c = ReactJS.useState[T](js.Any.fromFunction0[T](initial))
    (c._1, c._2.asInstanceOf[js.Function1[js.Function1[T,T], Unit]])
  }

  /** Initial value is strict. Set new value directly. Don't use this. */
  def useStateStrictDirect[T](initial: T) = {
    val c = ReactJS.useState[T](initial.asInstanceOf[js.Any])
    (c._1, c._2.asInstanceOf[js.Function1[T, Unit]])
  }

  /** Initial value is a "lazy". Set new value directly. Don't use this. */
  def useStateDirect[T](initial: () => T) = {
    val c = ReactJS.useState[T](js.Any.fromFunction0[T](initial))
    (c._1, c._2.asInstanceOf[js.Function1[T, Unit]])
  }

  def useReducer[S, A](reducer: (S,A)=>S, initialState: S): (S, Dispatch[A]) = {
    val c = ReactJS.useReducer(reducer, initialState)
    (c._1, c._2)
  }

  def useReducer[S, A, I](reducer: (S,A)=>S, initialArg: I, init: I => S): (S, Dispatch[A]) = {
    val c = ReactJS.useReducer(reducer, initialArg, init)
    (c._1, c._2)
  }

  /** Effect is run on every render which is probably too much. */
  def useEffectAlways(didUpdate: EffectArg) =
    ReactJS.useEffect(didUpdate, undefinedDependencies)

  /** Effect is run when dependencies change. */
  def useEffect(didUpdate: EffectArg, dependencies: Dependencies) =
    ReactJS.useEffect(didUpdate, dependencies)

  /** Effect is run when dependencies change. */
  def useEffect(dependencies: js.Any*)(didUpdate: EffectArg) =
    ReactJS.useEffect(didUpdate, dependencies.toJSArray)

  /** Effect is run when dependencies change. */
  def useEffect(dependencies: Dependencies)(didUpdate: EffectArg) =
    ReactJS.useEffect(didUpdate, dependencies)

  /** Effect is run at mount/unmount time with `[]` as the 2nd argument. */
  def useEffectMounting(didUpdate: EffectArg) =
    ReactJS.useEffect(didUpdate, emptyDependencies)

  /** Run before browser updates the screen. Kinda a "sync" effect. */
  def useLayoutEffect(didUpdate: EffectArg, dependencies: Dependencies) =
    ReactJS.useLayoutEffect(didUpdate, dependencies)

  /** Memoize a lazy value. */
  def useMemo[T](value: () => T, dependencies: Dependencies): T =
    ReactJS.useMemo[T](value, dependencies)

  /** Memoize a lazy value. */
  def useMemo[T](dependencies: js.Any*)(value: () => T): T =
    ReactJS.useMemo[T](value, dependencies.toJSArray)

  /** Memoize a lazy value. */
  def useMemo[T](dependencies: Dependencies)(value: js.Function0[T]): T =
    ReactJS.useMemo[T](value, dependencies)

  /** Memoize a lazy value. Set on mounting. */
  def useMemoMounting[T](value: () => T): T = ReactJS.useMemo[T](value, emptyDependencies)

  def useDebugValue[T](value: T): Unit =
    ReactJS.useDebugValue[T](value, js.undefined)

  def useDebugValue[T](value: T, format: T => String): Unit =
    ReactJS.useDebugValue[T](value, js.Any.fromFunction1[T, String](format))

  /** Default API is for no-arg callback. Declare as val yourCB = useCallback... */
  def useCallback[T](callback: js.Function0[T], dependencies: Dependencies): js.Function0[T] =
    ReactJS.useCallback(callback, dependencies).asInstanceOf[js.Function0[T]]

  def useCallback[T](dependencies: Dependencies)(callback: js.Function0[T]): js.Function0[T] =
    ReactJS.useCallback(callback, dependencies).asInstanceOf[js.Function0[T]]

  def useCallback[T](dependencies: js.Any*)(callback: js.Function0[T]): js.Function0[T] =
    ReactJS.useCallback(callback, dependencies.toJSArray).asInstanceOf[js.Function0[T]]

  def useCallback[A1, T](callback: js.Function1[A1, T], dependencies: Dependencies): js.Function1[A1,T] =
    ReactJS.useCallback(callback, dependencies).asInstanceOf[js.Function1[A1,T]]

  def useCallback[A1, T](dependencies: js.Any*)(callback: js.Function1[A1, T]): js.Function1[A1,T] =
    ReactJS.useCallback(callback, dependencies.toJSArray).asInstanceOf[js.Function1[A1,T]]

  def useCallback[A1, A2, T](callback: (A1,A2) => T, dependencies: Dependencies): js.Function2[A1,A2,T] =
    ReactJS.useCallback(
      js.Any.fromFunction2[A1,A2,T](callback), dependencies).asInstanceOf[js.Function2[A1,A2,T]]

  def useCallback[A1, A2, T](dependencies: js.Any*)(callback: js.Function2[A1,A2,T]): js.Function2[A1,A2,T] =
    ReactJS.useCallback(callback, dependencies.toJSArray).asInstanceOf[js.Function2[A1,A2,T]]

  def useCallback[A1, A2, A3, T](callback: (A1,A2,A3) => T, dependencies: Dependencies): js.Function3[A1,A2,A3,T] =
    ReactJS.useCallback(
      js.Any.fromFunction3[A1,A2,A3,T](callback), dependencies).asInstanceOf[js.Function3[A1,A2,A3,T]]

  def useCallback[A1, A2, A3, A4, T](callback: (A1,A2,A3,A4) => T, dependencies: Dependencies): js.Function4[A1,A2,A3,A4,T] =
    ReactJS.useCallback(
      js.Any.fromFunction4[A1,A2,A3,A4,T](callback), dependencies).asInstanceOf[js.Function4[A1,A2,A3,A4,T]]

  def useCallback[A1, A2, A3, A4, A5, T](callback: (A1,A2,A3,A4,A5) => T, dependencies: Dependencies): js.Function5[A1,A2,A3,A4,A5,T] =
    ReactJS.useCallback(
      js.Any.fromFunction5[A1,A2,A3,A4,A5,T](callback), dependencies).asInstanceOf[js.Function5[A1,A2,A3,A4,A5,T]]

  def useRef[T](initialValue: T): MutableRef[T] = ReactJS.useRef[T](initialValue)

  /** Expose imperative functions in R to refs.
   * @tparam T Ref type
   * @tparam R js object whose properties are functions. This is not enforced in
   * the type.
   */
  def useImperativeHandle[T, R <: js.Object](ref: MutableRef[T],
    thunk: () => R, dependencies: Dependencies): Unit =
    ReactJS.useImperativeHandle[T,R](ref, thunk, dependencies)

  def `lazy`(lazyComponent: DynamicImportThunk): ReactJsLazyComponent =
    ReactJS.`lazy`(lazyComponent)

  def useTransition(config: TransitionConfig) = {
    val p = ReactJS.useTransition(config)
    (p._1.asInstanceOf[js.Function1[js.Function0[Unit], Unit]], p._2.asInstanceOf[Boolean])
  }

  /** Use a deferred value. Config indicats how long the value is good for. */
  def useDeferredValue[T](value: T, config: DeferredValueConfig): T =
    ReactJS.useDeferredValue(value.asInstanceOf[js.Any], config).asInstanceOf[T]

  //val Fragment = ReactJS.Fragment
  //val StrictMode = ReactJS.StrictMode
  //val Suspense = ReactJS.Suspense
  //val SuspenseList = ReactJS.SuspenseList  
  val Children = ReactJS.Children

  /** Create a React.fragment element. */
  object Fragment {
    def apply(key: Option[String] = None)(children: ReactNode*) =
      React.createFragment(key, children: _*)

    /** Preferred creation function. */
    def apply(children: ReactNode*) =
      React.createFragment(None, children: _*)
  }

  /** Strict element. Wraps your root component typically. */
  object StrictMode {
    /** Wrap some children with a Strict component. */
    def apply(children: ReactNode*): ReactNode =
      ReactJS.createElement(ReactJS.StrictMode, null, children:_*)
  }

  /** Catch a thrown js.Promise from the child. Show fallback until the promise is
   * resolved.
   */
  object Suspense {
    def apply(fallback: => ReactNode/* | Null = null*/)(children: => ReactNode) =
      ReactJS.createElement(
        ReactJS.Suspense,
        lit("fallback" -> fallback.asInstanceOf[js.Any]),
        children
      )
  }

  object SuspenseList {
    def apply(items: ReactNode*) =
      ReactJS.createElement(ReactJS.SuspenseList, null, items:_*)
  }

}

/** Primary entry point into the React API. */
@deprecated("Use main react object react e.g. react.createElement().")
object React extends React

/**
  * React's context object contains the consumer and provider "components".
  */
@js.native
trait ReactContext[T] extends js.Object {
  type ValueType = T

  /** Only takes a single attribute value, "value" with the context . */
  val Provider: ReactJsComponent = js.native
  val Consumer: ReactJsComponent = js.native

  /** Not public API. */
  var currentValue: T = js.native

  /** Not public API. */
  val defaultValue: T = js.native
}