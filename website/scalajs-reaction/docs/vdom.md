---
id: vdom
title: Virtual DOM
---

A simple "virtual DOM" has been provided. For facade libraries, this usually
means some way to create HTML elements with attributes and children. It does
*not* mean a virtual dom implementation since react already provides that.

`scalajs-react-vdom` is a separate vdom that you can use if you want to. You can
another project's vdom as well as long as the final "element" is typed as a
`ReactNode`.

To you use the vdom, do
```scala
import ttg.react.vdom._
```

This import provides you the ability to create attributes as a list, as compared
to non-native JS traits and create elements using their name.

```scala
// create a div, ReactDomElement < ReactNode
val aDiv: ReactDomElement = div(...attributes...)(..children...)
```

The 2 parameter list (which allows you to easily curry as well) helps break up
the lists between attributes and children which improves type
inference. Children need to be `ReactNode` or a subclass of this such as
`ReactElement`. An `import ttg.react.implicits._` will bring in an implicit
conversation from `Component` to `ReactElement` for you. Otherwise on each child
you must call `elements.createElement` or use a syntax import and do
`yourComponent.toEl`. The implicits make it easy.

The first parameter list takes a statically defined attribute trait whose name
is the capitalized version of the element:

```scala
val aDiv = div(new DivProps { className = "myDivClassName" })("child1", "child2")
```

vdom html elements create ReactNodes directly.

If you only have children and no attribute, skip the first set of parenthesis,

```scala
val aDiv = div("child1", "child2")
```

### Attribute List Based VDOM

Another attribute list based vdom is available by importing
`vdom.prefix_<^._`. Elements should be prefixed with `<.` as in `<.div` and
attributes are specified using `^.` as in `^.className`.

The attribute list VDOM has less type safety and allows you to add attributes
outside the prescribed list based on a statically defined trait, but it can be
more convenient at times to use this syntax.

The funny symbol `<.` is meant to look like an element since scalajs-react does
not have a JSX macro/processor. Attributes start with `^.` as in
`^.className`. If you import both `import ^._` and `import ^._` you can do away
with those symbols completely at the risk of having a name clash. If define your
own symbols such as `E` or `A`, you can use those. Just repeat the definition of
those symbols as they are defined in ttg.react.redux package object and use
yours.

The attributes in a list are automatically converted to a js.Object behind the
scenes. Attributes that result in a js.undefined value are removed from the
javascript object automatically.

In its current form, the attributes are a list converted to a props, which is
not really typesafe from the perspective the attributes or the props "set of
attributes." To improve this we need to use non-native JS traits which I have
not provided, then provide the functions to use the props as well as perhaps the
list of attributes. The non-native JS trait versions of these vdom elements will
be added shortly.

The list based vdom allows you to specify style attributes in a list using
`style = Style(...)`. See the next section for more safe version of creating
styles.

## Styling

A simple, slightly more safe inline style capability has also been provided.

```scala
import vdom._
val style1 = new StyleAttr { display: "flex" }
val style2 = new StyleAttr { flexDirection: "column" }

import ttg.react.vdom.style._
// style2 takes precedence
val flexVertical = merge(style1, style2)
```
you can add style attribute anything missing via:
```scala
val unsafeStyle = unsafeAddProp(style1, "foo", "bar")
```

You would use these as the "value" in the list of attributes for the vdom elements.
