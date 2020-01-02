(window.webpackJsonp=window.webpackJsonp||[]).push([[32],{78:function(e,t,a){"use strict";a.r(t),a.d(t,"frontMatter",function(){return s}),a.d(t,"rightToc",function(){return l}),a.d(t,"default",function(){return p});a(0);var n=a(92);function i(){return(i=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var a=arguments[t];for(var n in a)Object.prototype.hasOwnProperty.call(a,n)&&(e[n]=a[n])}return e}).apply(this,arguments)}function r(e,t){if(null==e)return{};var a,n,i=function(e,t){if(null==e)return{};var a,n,i={},r=Object.keys(e);for(n=0;n<r.length;n++)a=r[n],t.indexOf(a)>=0||(i[a]=e[a]);return i}(e,t);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);for(n=0;n<r.length;n++)a=r[n],t.indexOf(a)>=0||Object.prototype.propertyIsEnumerable.call(e,a)&&(i[a]=e[a])}return i}var s={id:"vdom",title:"Virtual DOM"},l=[{value:"Attribute List Based VDOM",id:"attribute-list-based-vdom",children:[]},{value:"Styling",id:"styling",children:[]}],o={rightToc:l},c="wrapper";function p(e){var t=e.components,a=r(e,["components"]);return Object(n.b)(c,i({},o,a,{components:t,mdxType:"MDXLayout"}),Object(n.b)("p",null,'scalajs-reaction has a "virtual DOM."\nThe vdom is there to help you create elements and their properties\nmore easily. ',Object(n.b)("inlineCode",{parentName:"p"},"vdom")," is a separate package "),Object(n.b)("p",null,"To you use the vdom, do"),Object(n.b)("pre",null,Object(n.b)("code",i({parentName:"pre"},{className:"language-scala"}),"import react.vdom._\nimport tags._ // to import standard HTML vdom tags\n")),Object(n.b)("p",null,"This import also provides you the ability to create attributes as a list (discussed\nbelow), compared\nto using non-native JS traits, and create elements using their name."),Object(n.b)("pre",null,Object(n.b)("code",i({parentName:"pre"},{className:"language-scala"}),"// create a div, ReactDomElement < ReactNode\nval aDiv: ReactDomElement = div(...attributes...)(..children...)\n")),Object(n.b)("p",null,"The 2 parameter list (which allows you to easily curry as well) helps break up\nthe lists between attributes and children which improves type\ninference. Children need to be ",Object(n.b)("inlineCode",{parentName:"p"},"ReactNode")," or a subclass of this such as\n",Object(n.b)("inlineCode",{parentName:"p"},"ReactElement"),". An ",Object(n.b)("inlineCode",{parentName:"p"},"import react.implicits._")," will bring in an implicit\nconversion to a ",Object(n.b)("inlineCode",{parentName:"p"},"ReactNode")," for you. "),Object(n.b)("p",null,"The first parameter list takes a statically defined attribute trait whose name\nis the capitalized version of the element:"),Object(n.b)("pre",null,Object(n.b)("code",i({parentName:"pre"},{className:"language-scala"}),'val aDiv = div(new DivProps { className = "myDivClassName" })("child1", "child2")\n')),Object(n.b)("p",null,"If you only have children and no attribute, skip the first set of parenthesis,"),Object(n.b)("pre",null,Object(n.b)("code",i({parentName:"pre"},{className:"language-scala"}),'val aDiv = div("child1", "child2")\n')),Object(n.b)("p",null,"In the case of div, you can use a pre-defined helper:"),Object(n.b)("pre",null,Object(n.b)("code",i({parentName:"pre"},{className:"language-scala"}),'divWithClassname("my-class-name",\n  child1,\n  child2\n)\n')),Object(n.b)("h3",null,Object(n.b)("a",i({parentName:"h3"},{"aria-hidden":!0,className:"anchor",id:"attribute-list-based-vdom"})),Object(n.b)("a",i({parentName:"h3"},{"aria-hidden":!0,className:"hash-link",href:"#attribute-list-based-vdom"}),"#"),"Attribute List Based VDOM"),Object(n.b)("p",null,"Another attribute list based vdom is available by importing\n",Object(n.b)("inlineCode",{parentName:"p"},"vdom.prefix_<^._"),". Elements should be prefixed with ",Object(n.b)("inlineCode",{parentName:"p"},"<.")," as in ",Object(n.b)("inlineCode",{parentName:"p"},"<.div")," and\nattributes are specified using ",Object(n.b)("inlineCode",{parentName:"p"},"^.")," as in ",Object(n.b)("inlineCode",{parentName:"p"},"^.className"),". This is alot\nlike the API that scalajs-reaction (not scalajs-reaction) uses and is\ncommon in other scala.js facade libraries. I recommend not using this\nstyle though."),Object(n.b)("p",null,"The attribute list VDOM has less type safety and allows you to add attributes\noutside the prescribed list based on a statically defined trait, but it can be\nmore convenient at times to use this syntax."),Object(n.b)("p",null,"The funny symbol ",Object(n.b)("inlineCode",{parentName:"p"},"<.")," is meant to look like an element since scalajs-react does\nnot have a JSX macro/processor. Attributes start with ",Object(n.b)("inlineCode",{parentName:"p"},"^.")," as in\n",Object(n.b)("inlineCode",{parentName:"p"},"^.className"),". If you import both ",Object(n.b)("inlineCode",{parentName:"p"},"import ^._")," and ",Object(n.b)("inlineCode",{parentName:"p"},"import ^._")," you can do away\nwith those symbols completely at the risk of having a name clash. If define your\nown symbols such as ",Object(n.b)("inlineCode",{parentName:"p"},"E")," or ",Object(n.b)("inlineCode",{parentName:"p"},"A"),", you can use those. Just repeat the definition of\nthose symbols as they are defined in ttg.react.redux package object and use\nyours."),Object(n.b)("p",null,"The attributes in a list are automatically converted to a js.Object behind the\nscenes. Attributes that result in a js.undefined value are removed from the\njavascript object automatically."),Object(n.b)("p",null,'In its current form, the attributes are a list converted to a props, which is\nnot really typesafe from the perspective the attributes or the props "set of\nattributes." To improve this we need to use non-native JS traits which I have\nnot provided, then provide the functions to use the props as well as perhaps the\nlist of attributes. The non-native JS trait versions of these vdom elements will\nbe added shortly.'),Object(n.b)("p",null,"The list based vdom allows you to specify style attributes in a list using\n",Object(n.b)("inlineCode",{parentName:"p"},"style = Style(...)"),". See the next section for more safe version of creating\nstyles."),Object(n.b)("h2",null,Object(n.b)("a",i({parentName:"h2"},{"aria-hidden":!0,className:"anchor",id:"styling"})),Object(n.b)("a",i({parentName:"h2"},{"aria-hidden":!0,className:"hash-link",href:"#styling"}),"#"),"Styling"),Object(n.b)("p",null,"A simple, slightly more safe inline style capability has also been provided."),Object(n.b)("pre",null,Object(n.b)("code",i({parentName:"pre"},{className:"language-scala"}),'import vdom._\nval style1 = new StyleAttr { display: "flex" }\nval style2 = new StyleAttr { flexDirection: "column" }\n\nimport react.vdom.style._\n// style2 takes precedence\nval flexVertical = style1.combine(style2)\n')),Object(n.b)("p",null,"you can add style attribute anything missing via:"),Object(n.b)("pre",null,Object(n.b)("code",i({parentName:"pre"},{className:"language-scala"}),'val unsafeStyle = unsafeAddProp(style1, "foo", "bar")\n')),Object(n.b)("p",null,'You would use these as the "value" in the list of attributes for the vdom elements.'))}p.isMDXComponent=!0},92:function(e,t,a){"use strict";a.d(t,"a",function(){return l}),a.d(t,"b",function(){return b});var n=a(0),i=a.n(n),r=i.a.createContext({}),s=function(e){var t=i.a.useContext(r),a=t;return e&&(a="function"==typeof e?e(t):Object.assign({},t,e)),a},l=function(e){var t=s(e.components);return i.a.createElement(r.Provider,{value:t},e.children)},o="mdxType",c={inlineCode:"code",wrapper:function(e){return i.a.createElement(i.a.Fragment,{},e.children)}},p=function(e){var t=e.components,a=e.mdxType,n=e.originalType,r=e.parentName,l=function(e,t){var a={};for(var n in e)Object.prototype.hasOwnProperty.call(e,n)&&-1===t.indexOf(n)&&(a[n]=e[n]);return a}(e,["components","mdxType","originalType","parentName"]),o=s(t);return i.a.createElement(o[r+"."+a]||o[a]||c[a]||n,t?Object.assign({},l,{components:t}):l)};function b(e,t){var a=arguments,n=t&&t.mdxType;if("string"==typeof e||n){var r=a.length,s=new Array(r);s[0]=p;var l={};for(var c in t)hasOwnProperty.call(t,c)&&(l[c]=t[c]);l.originalType=e,l[o]="string"==typeof e?e:n,s[1]=l;for(var b=2;b<r;b++)s[b]=a[b];return i.a.createElement.apply(null,s)}return i.a.createElement.apply(null,a)}p.displayName="MDXCreateElement"}}]);