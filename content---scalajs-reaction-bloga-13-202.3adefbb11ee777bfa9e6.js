(window.webpackJsonp=window.webpackJsonp||[]).push([[13],{42:function(e,n,t){"use strict";t.r(n),t.d(n,"frontMatter",function(){return c}),t.d(n,"rightToc",function(){return i}),t.d(n,"default",function(){return s});t(0);var r=t(92);function a(){return(a=Object.assign||function(e){for(var n=1;n<arguments.length;n++){var t=arguments[n];for(var r in t)Object.prototype.hasOwnProperty.call(t,r)&&(e[r]=t[r])}return e}).apply(this,arguments)}function o(e,n){if(null==e)return{};var t,r,a=function(e,n){if(null==e)return{};var t,r,a={},o=Object.keys(e);for(r=0;r<o.length;r++)t=o[r],n.indexOf(t)>=0||(a[t]=e[t]);return a}(e,n);if(Object.getOwnPropertySymbols){var o=Object.getOwnPropertySymbols(e);for(r=0;r<o.length;r++)t=o[r],n.indexOf(t)>=0||Object.prototype.propertyIsEnumerable.call(e,t)&&(a[t]=e[t])}return a}var c={id:"changes",title:"Package and namespace changes",author:"aappddeevv",authorTitle:"scalajs-reaction core team",authorURL:"https://github.com/aappddevv",authorImageURL:"https://avatars1.githubusercontent.com/u/1183337?v=4",tags:["scalajs-reaction"]},i=[],p={rightToc:i},u="wrapper";function s(e){var n=e.components,t=o(e,["components"]);return Object(r.b)(u,a({},p,t,{components:n,mdxType:"MDXLayout"}),Object(r.b)("p",null,"The jar file group and package names have been shortened to remove\nscalajs-reaction from the names. This makes it easier to\nspecify them in your build.sbt file."),Object(r.b)("p",null,'The package hierarchy has also changed. Since you are\nonly likely to include one react package in your application\nthe use of "ttg" was burdonsome. For most of the core\npackages, the ttg package prefix has been removed. For\nexample instead of importing ',Object(r.b)("inlineCode",{parentName:"p"},"import ttg.react._")," you\nwould now just do ",Object(r.b)("inlineCode",{parentName:"p"},"import react._"),"."))}s.isMDXComponent=!0},92:function(e,n,t){"use strict";t.d(n,"a",function(){return i}),t.d(n,"b",function(){return l});var r=t(0),a=t.n(r),o=a.a.createContext({}),c=function(e){var n=a.a.useContext(o),t=n;return e&&(t="function"==typeof e?e(n):Object.assign({},n,e)),t},i=function(e){var n=c(e.components);return a.a.createElement(o.Provider,{value:n},e.children)},p="mdxType",u={inlineCode:"code",wrapper:function(e){return a.a.createElement(a.a.Fragment,{},e.children)}},s=function(e){var n=e.components,t=e.mdxType,r=e.originalType,o=e.parentName,i=function(e,n){var t={};for(var r in e)Object.prototype.hasOwnProperty.call(e,r)&&-1===n.indexOf(r)&&(t[r]=e[r]);return t}(e,["components","mdxType","originalType","parentName"]),p=c(n);return a.a.createElement(p[o+"."+t]||p[t]||u[t]||r,n?Object.assign({},i,{components:n}):i)};function l(e,n){var t=arguments,r=n&&n.mdxType;if("string"==typeof e||r){var o=t.length,c=new Array(o);c[0]=s;var i={};for(var u in n)hasOwnProperty.call(n,u)&&(i[u]=n[u]);i.originalType=e,i[p]="string"==typeof e?e:r,c[1]=i;for(var l=2;l<o;l++)c[l]=t[l];return a.a.createElement.apply(null,c)}return a.a.createElement.apply(null,t)}s.displayName="MDXCreateElement"}}]);