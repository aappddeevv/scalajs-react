(window.webpackJsonp=window.webpackJsonp||[]).push([[7],{84:function(e,a,t){"use strict";t.r(a);var r=t(0),n=t.n(r),l=t(96),c=t(99),o=t(93);a.default=function(e){var a=e.metadata,t=e.items,r=a.allTagsPath,m=a.name,i=a.count;return n.a.createElement(l.a,{title:'Blog | Tagged "'.concat(m,'"'),description:'Blog | Tagged "'.concat(m,'"')},n.a.createElement("div",{className:"container margin-vert--xl"},n.a.createElement("div",{className:"row"},n.a.createElement("div",{className:"col col--8 col--offset-2"},n.a.createElement("h1",null,i,' post(s) tagged with "',m,'"'),n.a.createElement(o.a,{href:r},"View All Tags"),n.a.createElement("div",{className:"margin-vert--xl"},t.map(function(e){var a=e.content,t=e.metadata;return n.a.createElement("div",{key:t.permalink},n.a.createElement(c.a,{frontMatter:a.frontMatter,metadata:t,truncated:!0},n.a.createElement(a,null)))}))))))}},92:function(e,a,t){"use strict";t.d(a,"a",function(){return o}),t.d(a,"b",function(){return u});var r=t(0),n=t.n(r),l=n.a.createContext({}),c=function(e){var a=n.a.useContext(l),t=a;return e&&(t="function"==typeof e?e(a):Object.assign({},a,e)),t},o=function(e){var a=c(e.components);return n.a.createElement(l.Provider,{value:a},e.children)},m="mdxType",i={inlineCode:"code",wrapper:function(e){return n.a.createElement(n.a.Fragment,{},e.children)}},s=function(e){var a=e.components,t=e.mdxType,r=e.originalType,l=e.parentName,o=function(e,a){var t={};for(var r in e)Object.prototype.hasOwnProperty.call(e,r)&&-1===a.indexOf(r)&&(t[r]=e[r]);return t}(e,["components","mdxType","originalType","parentName"]),m=c(a);return n.a.createElement(m[l+"."+t]||m[t]||i[t]||r,a?Object.assign({},o,{components:a}):o)};function u(e,a){var t=arguments,r=a&&a.mdxType;if("string"==typeof e||r){var l=t.length,c=new Array(l);c[0]=s;var o={};for(var i in a)hasOwnProperty.call(a,i)&&(o[i]=a[i]);o.originalType=e,o[m]="string"==typeof e?e:r,c[1]=o;for(var u=2;u<l;u++)c[u]=t[u];return n.a.createElement.apply(null,c)}return n.a.createElement.apply(null,t)}s.displayName="MDXCreateElement"},94:function(e,a,t){"use strict";var r=t(0),n=t.n(r),l=t(93),c=t(100),o=t(95),m=t.n(o);function i(){return(i=Object.assign||function(e){for(var a=1;a<arguments.length;a++){var t=arguments[a];for(var r in t)Object.prototype.hasOwnProperty.call(t,r)&&(e[r]=t[r])}return e}).apply(this,arguments)}a.a={code:function(e){var a=e.children;return"string"==typeof a?n.a.createElement(c.a,e):a},a:l.a,pre:function(e){return n.a.createElement("pre",i({className:m.a.mdxCodeBlock},e))}}},95:function(e,a,t){e.exports={mdxCodeBlock:"mdxCodeBlock_iHAB0Zg7"}},99:function(e,a,t){"use strict";var r=t(0),n=t.n(r),l=t(92),c=t(93),o=t(94);a.a=function(e){var a,t,r,m,i,s=e.children,u=e.frontMatter,p=e.metadata,d=e.truncated,g=p.date,v=p.permalink,f=p.tags,E=u.author,h=u.authorURL,y=u.authorTitle,b=u.authorFBID,N=u.title;return n.a.createElement("div",null,(a=g.substring(0,10).split("-"),t=a[0],r=["January","February","March","April","May","June","July","August","September","October","November","December"][parseInt(a[1],10)-1],m=parseInt(a[2],10),i=b?"https://graph.facebook.com/".concat(b,"/picture/?height=200&width=200"):u.authorImageURL,n.a.createElement("header",null,n.a.createElement("h1",{className:"margin-bottom--xs"},n.a.createElement(c.a,{to:v},N)),n.a.createElement("div",{className:"margin-bottom--sm"},n.a.createElement("small",null,r," ",m,", ",t)),n.a.createElement("div",{className:"avatar margin-bottom--md"},i&&n.a.createElement("a",{className:"avatar__photo-link",href:h,target:"_blank",rel:"noreferrer noopener"},n.a.createElement("img",{className:"avatar__photo",src:i,alt:E})),n.a.createElement("div",{className:"avatar__intro"},E&&n.a.createElement(n.a.Fragment,null,n.a.createElement("h4",{className:"avatar__name"},n.a.createElement("a",{href:h,target:"_blank",rel:"noreferrer noopener"},E)),n.a.createElement("small",{className:"avatar__subtitle"},y)))))),n.a.createElement("article",{className:"markdown"},n.a.createElement(l.a,{components:o.a},s)),n.a.createElement("div",{className:"row margin-vert--lg"},n.a.createElement("div",{className:"col"},f.length>0&&n.a.createElement(n.a.Fragment,null,n.a.createElement("strong",null,"Tags:"),f.map(function(e){var a=e.label,t=e.permalink;return n.a.createElement(c.a,{key:t,className:"margin-horiz--sm",to:t},a)}))),n.a.createElement("div",{className:"col text--right"},d&&n.a.createElement(c.a,{to:p.permalink},n.a.createElement("strong",null,"Read More")))))}}}]);