(window.webpackJsonp=window.webpackJsonp||[]).push([[5],{88:function(e,a,t){"use strict";t.r(a);var n=t(0),r=t.n(n),l=t(96),c=t(99),m=t(93);var i=function(e){var a=e.nextItem,t=e.prevItem;return r.a.createElement("nav",{className:"pagination-nav"},r.a.createElement("div",{className:"pagination-nav__item"},t&&r.a.createElement(m.a,{className:"pagination-nav__link",to:t.permalink},r.a.createElement("h5",{className:"pagination-nav__link--sublabel"},"Previous Post"),r.a.createElement("h4",{className:"pagination-nav__link--label"},"« ",t.title))),r.a.createElement("div",{className:"pagination-nav__item pagination-nav__item--next"},a&&r.a.createElement(m.a,{className:"pagination-nav__link",to:a.permalink},r.a.createElement("h5",{className:"pagination-nav__link--sublabel"},"Next Post"),r.a.createElement("h4",{className:"pagination-nav__link--label"},a.title," »"))))};a.default=function(e){var a=e.content,t=e.metadata,n=e.nextItem,m=e.prevItem,o=a.frontMatter;return r.a.createElement(l.a,{title:t.title,description:t.description},a&&r.a.createElement("div",{className:"container margin-vert--xl"},r.a.createElement("div",{className:"row"},r.a.createElement("div",{className:"col col--8 col--offset-2"},r.a.createElement(c.a,{frontMatter:o,metadata:t},r.a.createElement(a,null)),r.a.createElement("div",{className:"margin-vert--xl"},r.a.createElement(i,{nextItem:n,prevItem:m}))))))}},92:function(e,a,t){"use strict";t.d(a,"a",function(){return m}),t.d(a,"b",function(){return p});var n=t(0),r=t.n(n),l=r.a.createContext({}),c=function(e){var a=r.a.useContext(l),t=a;return e&&(t="function"==typeof e?e(a):Object.assign({},a,e)),t},m=function(e){var a=c(e.components);return r.a.createElement(l.Provider,{value:a},e.children)},i="mdxType",o={inlineCode:"code",wrapper:function(e){return r.a.createElement(r.a.Fragment,{},e.children)}},s=function(e){var a=e.components,t=e.mdxType,n=e.originalType,l=e.parentName,m=function(e,a){var t={};for(var n in e)Object.prototype.hasOwnProperty.call(e,n)&&-1===a.indexOf(n)&&(t[n]=e[n]);return t}(e,["components","mdxType","originalType","parentName"]),i=c(a);return r.a.createElement(i[l+"."+t]||i[t]||o[t]||n,a?Object.assign({},m,{components:a}):m)};function p(e,a){var t=arguments,n=a&&a.mdxType;if("string"==typeof e||n){var l=t.length,c=new Array(l);c[0]=s;var m={};for(var o in a)hasOwnProperty.call(a,o)&&(m[o]=a[o]);m.originalType=e,m[i]="string"==typeof e?e:n,c[1]=m;for(var p=2;p<l;p++)c[p]=t[p];return r.a.createElement.apply(null,c)}return r.a.createElement.apply(null,t)}s.displayName="MDXCreateElement"},94:function(e,a,t){"use strict";var n=t(0),r=t.n(n),l=t(93),c=t(100),m=t(95),i=t.n(m);function o(){return(o=Object.assign||function(e){for(var a=1;a<arguments.length;a++){var t=arguments[a];for(var n in t)Object.prototype.hasOwnProperty.call(t,n)&&(e[n]=t[n])}return e}).apply(this,arguments)}a.a={code:function(e){var a=e.children;return"string"==typeof a?r.a.createElement(c.a,e):a},a:l.a,pre:function(e){return r.a.createElement("pre",o({className:i.a.mdxCodeBlock},e))}}},95:function(e,a,t){e.exports={mdxCodeBlock:"mdxCodeBlock_iHAB0Zg7"}},99:function(e,a,t){"use strict";var n=t(0),r=t.n(n),l=t(92),c=t(93),m=t(94);a.a=function(e){var a,t,n,i,o,s=e.children,p=e.frontMatter,u=e.metadata,v=e.truncated,d=u.date,E=u.permalink,g=u.tags,f=p.author,h=p.authorURL,N=p.authorTitle,_=p.authorFBID,b=p.title;return r.a.createElement("div",null,(a=d.substring(0,10).split("-"),t=a[0],n=["January","February","March","April","May","June","July","August","September","October","November","December"][parseInt(a[1],10)-1],i=parseInt(a[2],10),o=_?"https://graph.facebook.com/".concat(_,"/picture/?height=200&width=200"):p.authorImageURL,r.a.createElement("header",null,r.a.createElement("h1",{className:"margin-bottom--xs"},r.a.createElement(c.a,{to:E},b)),r.a.createElement("div",{className:"margin-bottom--sm"},r.a.createElement("small",null,n," ",i,", ",t)),r.a.createElement("div",{className:"avatar margin-bottom--md"},o&&r.a.createElement("a",{className:"avatar__photo-link",href:h,target:"_blank",rel:"noreferrer noopener"},r.a.createElement("img",{className:"avatar__photo",src:o,alt:f})),r.a.createElement("div",{className:"avatar__intro"},f&&r.a.createElement(r.a.Fragment,null,r.a.createElement("h4",{className:"avatar__name"},r.a.createElement("a",{href:h,target:"_blank",rel:"noreferrer noopener"},f)),r.a.createElement("small",{className:"avatar__subtitle"},N)))))),r.a.createElement("article",{className:"markdown"},r.a.createElement(l.a,{components:m.a},s)),r.a.createElement("div",{className:"row margin-vert--lg"},r.a.createElement("div",{className:"col"},g.length>0&&r.a.createElement(r.a.Fragment,null,r.a.createElement("strong",null,"Tags:"),g.map(function(e){var a=e.label,t=e.permalink;return r.a.createElement(c.a,{key:t,className:"margin-horiz--sm",to:t},a)}))),r.a.createElement("div",{className:"col text--right"},v&&r.a.createElement(c.a,{to:u.permalink},r.a.createElement("strong",null,"Read More")))))}}}]);