(window.webpackJsonp=window.webpackJsonp||[]).push([[3],{120:function(e,a,t){e.exports={heroBanner:"heroBanner_2Ftp0qVd",buttons:"buttons_1Wc3oHpx",features:"features_P2SUP9op",featureImage:"featureImage_3Xqxmi9m"}},36:function(e,a,t){"use strict";t.r(a);var n=t(0),r=t.n(n),o=t(98),l=t.n(o),s=t(96),c=t(100),m=t(93),i=t(97),u=t.n(i),p=t(101),d=t.n(p),g=t(120),E=t.n(g),h=[{title:r.a.createElement(r.a.Fragment,null,"Easy to Use"),imageUrl:"img/undraw_docusaurus_mountain.svg",description:r.a.createElement(r.a.Fragment,null,"scalajs-reaction uses reactjs hooks to make component creation and use easy.")},{title:r.a.createElement(r.a.Fragment,null,"Focus on What Matters"),imageUrl:"img/undraw_docusaurus_tree.svg",description:r.a.createElement(r.a.Fragment,null,"Your real focus should be on creating great web applications not on the framework or interop.")},{title:r.a.createElement(r.a.Fragment,null,"Powered by React"),imageUrl:"img/undraw_docusaurus_react.svg",description:r.a.createElement(r.a.Fragment,null,"It is all reactjs underneath the hood and interop is a breeze so you can drop down to js or typescript any time you want.")}];a.default=function(){var e=u()().siteConfig,a=void 0===e?{}:e;return r.a.createElement(s.a,{title:"Hello from ".concat(a.title),description:"scalajs-reaction documentation"},r.a.createElement("header",{className:l()("hero hero--primary",E.a.heroBanner)},r.a.createElement("div",{className:"container"},r.a.createElement("h1",{className:"hero__title"},a.title),r.a.createElement("p",{className:"hero__subtitle"},a.tagline),r.a.createElement("div",{className:E.a.buttons},r.a.createElement(m.a,{className:l()("button button--outline button--secondary button--lg",E.a.getStarted),to:d()("docs/intro")},"Get Started")))),r.a.createElement("main",null,h&&h.length&&r.a.createElement("section",{className:E.a.features},r.a.createElement("div",{className:"container"},r.a.createElement("div",{className:"row"},h.map(function(e,a){var t=e.imageUrl,n=e.title,o=e.description;return r.a.createElement("div",{key:a,className:l()("col col--4",E.a.feature)},t&&r.a.createElement("div",{className:"text--center"},r.a.createElement("img",{className:E.a.featureImage,src:d()(t),alt:n})),r.a.createElement("h3",null,n),r.a.createElement("p",null,o))})))),r.a.createElement("section",{className:E.a.features},r.a.createElement("div",{className:"container"},r.a.createElement("p",null,"It is easy to create a comment. Here is the hello world component. This creates a functional component that takes zero args."),r.a.createElement("p",null,r.a.createElement(c.a,{className:"scala"},'\n          val HelloWorld = SFC0 { div("hello world") }\n')),r.a.createElement("p",null,"A functional component that takes props (one argument) with a mandatory [name] property is"),r.a.createElement(c.a,{className:"scala"},'\nobject MyComponent {\n  trait Props extends js.Object {\n     val name: String\n  }\n  val sfc = SFC1[Props] { props =>\n    div("hello " + props.name)\n  }\n  def apply(props: Props) = sfc(props)\n}\n\n// use MyComponent\ndiv(\n  MyComponent(new Props { \n    val name = "world" \n  })\n)\n')))))}}}]);