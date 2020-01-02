(window.webpackJsonp=window.webpackJsonp||[]).push([[16],{80:function(e,n,t){"use strict";t.r(n),t.d(n,"frontMatter",function(){return r}),t.d(n,"rightToc",function(){return o}),t.d(n,"default",function(){return u});t(0);var a=t(92);function l(){return(l=Object.assign||function(e){for(var n=1;n<arguments.length;n++){var t=arguments[n];for(var a in t)Object.prototype.hasOwnProperty.call(t,a)&&(e[a]=t[a])}return e}).apply(this,arguments)}function i(e,n){if(null==e)return{};var t,a,l=function(e,n){if(null==e)return{};var t,a,l={},i=Object.keys(e);for(a=0;a<i.length;a++)t=i[a],n.indexOf(t)>=0||(l[t]=e[t]);return l}(e,n);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(a=0;a<i.length;a++)t=i[a],n.indexOf(t)>=0||Object.prototype.propertyIsEnumerable.call(e,t)&&(l[t]=e[t])}return l}var r={id:"data_interop",title:"Data Interop"},o=[{value:"JS -> Scala",id:"js---scala",children:[]},{value:"Scala -> JS",id:"scala---js",children:[]},{value:"pimps",id:"pimps",children:[]},{value:"Don't Forget About a Structural Check",id:"dont-forget-about-a-structural-check",children:[]},{value:"TL;DR",id:"tldr",children:[]}],s={rightToc:o},c="wrapper";function u(e){var n=e.components,t=i(e,["components"]);return Object(a.b)(c,l({},s,t,{components:n,mdxType:"MDXLayout"}),Object(a.b)("p",null,"Since there is an emphasis on interop with javascript reactjs components, you\nneed to also interop with javascript data. Scala.js offers ",Object(a.b)("inlineCode",{parentName:"p"},"js.UndefOr"),' to\ninterop with fields in data structures that do not exist. Of course, in\njavascript a value can be undefined or null and sometimes they mean the same\nthing and sometimes not to an API. Sometimes APIs are not precise and either\nundefined or null means "not present."'),Object(a.b)("p",null,"scala itself offers the ability to assign ",Object(a.b)("inlineCode",{parentName:"p"},"null"),"\n(subclass of ",Object(a.b)("inlineCode",{parentName:"p"},"Null"),') to most values e.g. a String can be a value ("george"),\nempty ("") or null.'),Object(a.b)("p",null,"The key message is that even with a typescript description to guide you,\nyou need to be defensive since null and undefined are often used interchangeably\nin javascript since its so easy to do ",Object(a.b)("inlineCode",{parentName:"p"},"a ? ...")," or ",Object(a.b)("inlineCode",{parentName:"p"},"!!a")," ior ",Object(a.b)("inlineCode",{parentName:"p"},"if(a) ..."),' and if a is null or undefined,\nit will evaluate to "false".'),Object(a.b)("h2",null,Object(a.b)("a",l({parentName:"h2"},{"aria-hidden":!0,className:"anchor",id:"js---scala"})),Object(a.b)("a",l({parentName:"h2"},{"aria-hidden":!0,className:"hash-link",href:"#js---scala"}),"#"),"JS -> Scala"),Object(a.b)("p",null,"Given that we also use native and non-native JS traits to describe the shape of\ndata, we may wonder how to handle data from web APIs, for example in the\nstructure:"),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{className:"language-typescript"}),"export interface Foo { \n    field1?: string\n    field2?: string | null\n}\n")),Object(a.b)("p",null,"field1 can be undefined or a string and field2 can be a string value, undefined\nor null. Depending on the nature of the data source, you may encounter alot of\nfield1 or field2 on some combination of that. For example, field1 could\nrepresent a value that is non-nullable in the source database but may not have\nbeen requested in a fetch and may not be present. field2 could represent a\nnullable field in the source database that was not requested or was requested\nand was null."),Object(a.b)("p",null,"The question is how to best model these in scala so that we do not have to write\nan object conversions for each data structure. "),Object(a.b)("p",null,'If you know the data is either there or not there, then UndefOr is fine recognizing\nthat "null" is a valid value:'),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{className:"language-scala"}),"trait Foo extends js.Object {\n  var field: js.UndefOr[String] // var so mutable, use val for immutable\n}\n")),Object(a.b)("p",null,"However, if field can be null, undefined or a value what should we use? Consider:"),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{className:"language-scala"}),"trait Foo extends js.Object {\n  var field1: js.UndefOr[String]\n}\n")),Object(a.b)("p",null,"Here, the value may not be present but it could be null, which is ",Object(a.b)("em",{parentName:"p"},"different"),"\nthen not being present. We could test:"),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{className:"language-scala"}),"aFoo.field == null\n// or\naFoo.orNull == null\n")),Object(a.b)("p",null,"which returns true or false depending on whether the value is null. ",Object(a.b)("inlineCode",{parentName:"p"},"UndefOr"),"\nhas its own pimp which returns null if UndefOr is undefined, but not if its\nnull:"),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{className:"language-scala"}),"val x: jsUndefOr[String] = ...\nval test: String = x.orNull // test could be a string or null\n")),Object(a.b)("p",null,"What we generally need when obtaining data from javascript is something that\nrecognizes the idioms that if a value is undefined or null, then its \"false\" or\nNone, not just if its' undefined. UndefOr's ",Object(a.b)("inlineCode",{parentName:"p"},"isEmpty")," function just checks if\nits undefined, so its not quite right for our needs. What we need is to get to a\nscala Option (or something like that) when the value is null ",Object(a.b)("em",{parentName:"p"},"or")," undefined."),Object(a.b)("p",null,"scalajs-reaction provides a pimp:"),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{className:"language-scala"}),"final case class JsUndefOrOps[A](a: UndefOr[A]) {\n  def isNull  = a == null\n  def isEmpty = isNull || !a.isDefined\n}\n")),Object(a.b)("p",null,"which can be implicitly used by importing the syntax so you can test:"),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{className:"language-scala"}),"val aBoolean = aFoo.field1.isNull // is it just null?\nval aBoolean2 = afoo.field1.isEmpty // is it null or undefined\n")),Object(a.b)("p",null,"So we can test for null fairly easily given the ",Object(a.b)("inlineCode",{parentName:"p"},"UndefOr")," type definition. But\nwe want to manipulate these things with some more functional syntax. For\nexample, we would like an Option-based variant that is None if ",Object(a.b)("inlineCode",{parentName:"p"},"isEmpty==true"),"\nand the value otherwise. Using ",Object(a.b)("inlineCode",{parentName:"p"},"UndefOr.toOption"),' actually may wrap a "null"\nvalue and produce a ',Object(a.b)("inlineCode",{parentName:"p"},"Some(null)")," instead of None. So clearly, just using\n",Object(a.b)("inlineCode",{parentName:"p"},".toOption")," is not a good answer. So at the expense of a little boolean check,\nperhaps we could do something more convenient. "),Object(a.b)("p",null,"Let's state what we want, an\nOption that reflects both UndefOr and possible underlying null value:"),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{className:"language-scala"}),"final case class JsUndefOrOps[A](a: UndefOr[A]) {\n  @inline def toNonNullOption = if(a == null || a.isEmpty) None else a.toOption\n}\n")),Object(a.b)("p",null,'So now we can safely convert a UndefOr to an Option and have it be "null" if it\nis null or undefined and a Some with a non-null value otherwise. The cost you\npay is a small conversion charge. So in general, it\'s ok to model your data us\nUndefOr:'),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{className:"language-scala"})," val x: Option[String] = aFoo.field1.toNonNullOption\n")),Object(a.b)("p",null,"Also, if you need to set data to null and the trait defines it as an UndefOr,\njust set it directly to null, just before you dispatch it javascript code:"),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{className:"language-scala"}),"  aFoo.field1 = null\n")),Object(a.b)("p",null,"You should note that just declaring the attribute as a type:"),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{}),"trait Foo extends js.Object {\n  var field1: String\n}\n")),Object(a.b)("p",null,"does not communicate that it can be null but we know scala allows this. "),Object(a.b)("p",null,"Perhaps we should use\n",Object(a.b)("inlineCode",{parentName:"p"},"var field1: String|Null"),". There's nothing wrong with that except scala already\ndefines that String to be potentially null so why go through the hassle?\nSee below why we might want to be that explicit."),Object(a.b)("p",null,"If you see a trait with just a plain String and you want an Option just wrap it\njust like in, ",Object(a.b)("inlineCode",{parentName:"p"},"Option(field1)")," since Option translates null to None in plain\nscala to begin with."),Object(a.b)("h2",null,Object(a.b)("a",l({parentName:"h2"},{"aria-hidden":!0,className:"anchor",id:"scala---js"})),Object(a.b)("a",l({parentName:"h2"},{"aria-hidden":!0,className:"hash-link",href:"#scala---js"}),"#"),"Scala -> JS"),Object(a.b)("p",null,"The story is a bit easier on the Scala -> JS connection. You generally need this\nwhen stuffing data into a data structured destined for the js side of the world."),Object(a.b)("ul",null,Object(a.b)("li",{parentName:"ul"},"Have (a: Option","[T]","): ",Object(a.b)("ul",{parentName:"li"},Object(a.b)("li",{parentName:"ul"},Object(a.b)("inlineCode",{parentName:"li"},"a.orUndefined")," yields an UndefOr which is the type that will probably be\nin most of your non-native JS traits"),Object(a.b)("li",{parentName:"ul"},"Use ",Object(a.b)("inlineCode",{parentName:"li"},"a.getOrElse(null)")," to obtain the value or null for some non-native JS traits"))),Object(a.b)("li",{parentName:"ul"},"Have (a: A): ",Object(a.b)("ul",{parentName:"li"},Object(a.b)("li",{parentName:"ul"},"Wrap it ala ",Object(a.b)("inlineCode",{parentName:"li"},"js.defined(a)")," to get an ",Object(a.b)("inlineCode",{parentName:"li"},"UndefOr[A]")," to satisfy your trait's\ntype (if you used the approach above)."),Object(a.b)("li",{parentName:"ul"},"If the value could be null, you can wrap it in Option(a) then convert it as\ndescribed above to obtain an UndefOr. However if your JS target is ",Object(a.b)("inlineCode",{parentName:"li"},"A|null"),"\nthen there is nothing you need to do as scala and javascript both match the\ndesired semantics.")))),Object(a.b)("h2",null,Object(a.b)("a",l({parentName:"h2"},{"aria-hidden":!0,className:"anchor",id:"pimps"})),Object(a.b)("a",l({parentName:"h2"},{"aria-hidden":!0,className:"hash-link",href:"#pimps"}),"#"),"pimps"),Object(a.b)("p",null,"Check out\n",Object(a.b)("a",l({parentName:"p"},{href:"https://github.com/aappddeevv/scalajs-react/blob/master/scalajs-react-core/src/main/scala/syntax.scala"}),"syntax.scala"),"\nfor many pimps that you can apply to UndefOr values to help with data\nmanipulation on the scala side. There's alot there and its easy to forget them\nunless they are in a cheatsheet."),Object(a.b)("h2",null,Object(a.b)("a",l({parentName:"h2"},{"aria-hidden":!0,className:"anchor",id:"dont-forget-about-a-structural-check"})),Object(a.b)("a",l({parentName:"h2"},{"aria-hidden":!0,className:"hash-link",href:"#dont-forget-about-a-structural-check"}),"#"),"Don't Forget About a Structural Check"),Object(a.b)("p",null,"There is a little known converter in scala.js. If you have two traits that are\nnot related but have overlapping fields, you may need to cast one to the other\nwhat is essentially a structural cast, check out ",Object(a.b)("a",l({parentName:"p"},{href:"https://www.scala-js.org/api/scalajs-library/latest/#scala.scalajs.js.package@use%5BA%5D(x:A):scala.scalajs.js.Using%5BA%5D"}),"funky structural\ncast-kindof"),". It uses ",Object(a.b)("inlineCode",{parentName:"p"},"use"),"."),Object(a.b)("h2",null,Object(a.b)("a",l({parentName:"h2"},{"aria-hidden":!0,className:"anchor",id:"tldr"})),Object(a.b)("a",l({parentName:"h2"},{"aria-hidden":!0,className:"hash-link",href:"#tldr"}),"#"),"TL;DR"),Object(a.b)("p",null,"Let's assume that typescript is not properly annotated and some undefineds and nulls get mixed up. "),Object(a.b)("p",null,"If your typescript says:"),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{className:"language-typescript"}),"field1?: string\n")),Object(a.b)("p",null,"use this type of declaration in your trait:"),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{className:"language-scala"}),"var/val field1: js.UndefOr[String] = js.undefined\n")),Object(a.b)("p",null,"and use ",Object(a.b)("inlineCode",{parentName:"p"},"field1 = null")," if you want to set it to null or extract using\n",Object(a.b)("inlineCode",{parentName:"p"},"field1.toNonNullOption")," to extract an option more safely than just ",Object(a.b)("inlineCode",{parentName:"p"},"toOption"),"\nsince the value could secretly be a null."),Object(a.b)("p",null,"If typescript says:"),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{className:"language-typescript"}),"field1?: string|null\n")),Object(a.b)("p",null,"You can be more specific in scala.js than just saying its String as in scala\na value like that can be null. However, we can be more procise:"),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{className:"language-scala"}),"val field1: js.UndefOr[String|Null]\n")),Object(a.b)("p",null,"You have a choice of how explicit you want to make this. "),Object(a.b)("p",null,"If you use ",Object(a.b)("inlineCode",{parentName:"p"},"String|Null")," you can use an implicit ",Object(a.b)("inlineCode",{parentName:"p"},"absorbNull")," to change this to a ",Object(a.b)("inlineCode",{parentName:"p"},"js.UndefOr[String]"),"\nlike so ",Object(a.b)("inlineCode",{parentName:"p"},"field1.absorbNull")," or ",Object(a.b)("inlineCode",{parentName:"p"},"field1.absorbNullKeepTruthy"),".\nor you can map into it and flatten the ",Object(a.b)("inlineCode",{parentName:"p"},"String|Null")," via a ",Object(a.b)("inlineCode",{parentName:"p"},"field1.flatMap(_.toUndefOr)"),". If\nthe field was just ",Object(a.b)("inlineCode",{parentName:"p"},"val field1: String|Null")," then there is an implicit conversion\nfrom ",Object(a.b)("inlineCode",{parentName:"p"},"String|Null")," to ",Object(a.b)("inlineCode",{parentName:"p"},"js.UndefOr[]")," via ",Object(a.b)("inlineCode",{parentName:"p"},"aStringNull.toUndefOr")," which when flatMapped,\ngives you a ",Object(a.b)("inlineCode",{parentName:"p"},"js.UndefOr[String]"),". Again, you have a choice on how you want to do this."),Object(a.b)("p",null,"Check out the syntax extensions to get a feel for a wide range of how to treat this\ncommon javascript pattern. There are some specialized conversions for ",Object(a.b)("inlineCode",{parentName:"p"},"js.UndefOr[String|Null]"),"\nand ",Object(a.b)("inlineCode",{parentName:"p"},"js.UndefOr[Boolean|Null]"),"."),Object(a.b)("p",null,"If typescript says:"),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{className:"language-typescript"}),"field1: string|null\n")),Object(a.b)("p",null,"and you use "),Object(a.b)("pre",null,Object(a.b)("code",l({parentName:"pre"},{className:"language-scala"}),"var/val field1: String|Null\n")),Object(a.b)("p",null,"you can set it null if you need to: ",Object(a.b)("inlineCode",{parentName:"p"},"field1 = null"),"."),Object(a.b)("p",null,"Let's hope the typescript interface descriptions are correct!"),Object(a.b)("p",null,"Again, please see the syntax extensions package a multitude of ways to manage these nits."))}u.isMDXComponent=!0},92:function(e,n,t){"use strict";t.d(n,"a",function(){return o}),t.d(n,"b",function(){return p});var a=t(0),l=t.n(a),i=l.a.createContext({}),r=function(e){var n=l.a.useContext(i),t=n;return e&&(t="function"==typeof e?e(n):Object.assign({},n,e)),t},o=function(e){var n=r(e.components);return l.a.createElement(i.Provider,{value:n},e.children)},s="mdxType",c={inlineCode:"code",wrapper:function(e){return l.a.createElement(l.a.Fragment,{},e.children)}},u=function(e){var n=e.components,t=e.mdxType,a=e.originalType,i=e.parentName,o=function(e,n){var t={};for(var a in e)Object.prototype.hasOwnProperty.call(e,a)&&-1===n.indexOf(a)&&(t[a]=e[a]);return t}(e,["components","mdxType","originalType","parentName"]),s=r(n);return l.a.createElement(s[i+"."+t]||s[t]||c[t]||a,n?Object.assign({},o,{components:n}):o)};function p(e,n){var t=arguments,a=n&&n.mdxType;if("string"==typeof e||a){var i=t.length,r=new Array(i);r[0]=u;var o={};for(var c in n)hasOwnProperty.call(n,c)&&(o[c]=n[c]);o.originalType=e,o[s]="string"==typeof e?e:a,r[1]=o;for(var p=2;p<i;p++)r[p]=t[p];return l.a.createElement.apply(null,r)}return l.a.createElement.apply(null,t)}u.displayName="MDXCreateElement"}}]);