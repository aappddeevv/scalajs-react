(window.webpackJsonp=window.webpackJsonp||[]).push([[9],{121:function(e,n,t){e.exports={sidebar:"sidebar_Qb2Sh3ns",sidebarMenuIcon:"sidebarMenuIcon_1uhTlVQy",sidebarMenuCloseIcon:"sidebarMenuCloseIcon_3KMqXtyF"}},90:function(e,n,t){"use strict";t.r(n);var a=t(0),r=t.n(a),o=t(92),i=t(18),c=t(96),l=t(98),s=t.n(l),u=t(93),m=t(121),d=t.n(m);function p(e,n){return function(e){if(Array.isArray(e))return e}(e)||function(e,n){var t=[],a=!0,r=!1,o=void 0;try{for(var i,c=e[Symbol.iterator]();!(a=(i=c.next()).done)&&(t.push(i.value),!n||t.length!==n);a=!0);}catch(e){r=!0,o=e}finally{try{a||null==c.return||c.return()}finally{if(r)throw o}}return t}(e,n)||function(){throw new TypeError("Invalid attempt to destructure non-iterable instance")}()}var f=24;var b=function(e){var n=p(Object(a.useState)(!1),2),t=n[0],o=n[1],i=e.docsMetadata,c=e.sidebar;if(!c)return null;var l=i.docsSidebars[c];if(!l)throw new Error("Can not find ".concat(c," config"));var m=function e(n){switch(n.type){case"category":return r.a.createElement("li",{className:"menu__list-item",key:n.label},r.a.createElement("a",{className:"menu__link",href:"#!"},n.label),r.a.createElement("ul",{className:"menu__list"},n.items.map(e)));case"link":return r.a.createElement("li",{className:"menu__list-item",key:n.label},r.a.createElement(u.a,{activeClassName:"menu__link--active",className:"menu__link",to:n.href,onClick:function(){o(!1)}},n.label));case"ref":default:return e(function(e){var n=e.id,t=i.docs[n];if(!t)throw new Error("Improper sidebars file, document with id '".concat(n,"' not found."));return{type:"link",label:t.sidebar_label||t.title,href:t.permalink}}(n))}};return r.a.createElement("div",{className:d.a.sidebar},r.a.createElement("div",{className:s()("menu","menu--responsive",{"menu--show":t})},r.a.createElement("button",{"aria-label":t?"Close Menu":"Open Menu",className:"button button--secondary button--sm menu__button",type:"button",onClick:function(){o(!t)}},t?r.a.createElement("span",{className:s()(d.a.sidebarMenuIcon,d.a.sidebarMenuCloseIcon)},"×"):r.a.createElement("svg",{className:d.a.sidebarMenuIcon,xmlns:"http://www.w3.org/2000/svg",height:f,width:f,viewBox:"0 0 32 32",role:"img",focusable:"false"},r.a.createElement("title",null,"Menu"),r.a.createElement("path",{stroke:"currentColor",strokeLinecap:"round",strokeMiterlimit:"10",strokeWidth:"2",d:"M4 7h22M4 15h22M4 23h22"}))),r.a.createElement("ul",{className:"menu__list"},l.map(function(e){return m(e)}))))},v=t(94);n.default=function(e){var n=e.route,t=e.docsMetadata,a=e.location,l=t.permalinkToId,s=l[a.pathname]||l[a.pathname.replace(/\/$/,"")],u=t.docs[s]||{},m=u.sidebar,d=u.description,p=u.title,f=u.permalink,y=u.image;return r.a.createElement(c.a,{noFooter:!0,description:d,title:p,image:y,permalink:f},r.a.createElement("div",{className:"container container--fluid"},r.a.createElement("div",{className:"row"},r.a.createElement("div",{className:"col col--3"},r.a.createElement(b,{docsMetadata:t,sidebar:m})),r.a.createElement("main",{className:"col"},r.a.createElement(o.a,{components:v.a},Object(i.a)(n.routes,{docsMetadata:t}))))))}},92:function(e,n,t){"use strict";t.d(n,"a",function(){return c}),t.d(n,"b",function(){return m});var a=t(0),r=t.n(a),o=r.a.createContext({}),i=function(e){var n=r.a.useContext(o),t=n;return e&&(t="function"==typeof e?e(n):Object.assign({},n,e)),t},c=function(e){var n=i(e.components);return r.a.createElement(o.Provider,{value:n},e.children)},l="mdxType",s={inlineCode:"code",wrapper:function(e){return r.a.createElement(r.a.Fragment,{},e.children)}},u=function(e){var n=e.components,t=e.mdxType,a=e.originalType,o=e.parentName,c=function(e,n){var t={};for(var a in e)Object.prototype.hasOwnProperty.call(e,a)&&-1===n.indexOf(a)&&(t[a]=e[a]);return t}(e,["components","mdxType","originalType","parentName"]),l=i(n);return r.a.createElement(l[o+"."+t]||l[t]||s[t]||a,n?Object.assign({},c,{components:n}):c)};function m(e,n){var t=arguments,a=n&&n.mdxType;if("string"==typeof e||a){var o=t.length,i=new Array(o);i[0]=u;var c={};for(var s in n)hasOwnProperty.call(n,s)&&(c[s]=n[s]);c.originalType=e,c[l]="string"==typeof e?e:a,i[1]=c;for(var m=2;m<o;m++)i[m]=t[m];return r.a.createElement.apply(null,i)}return r.a.createElement.apply(null,t)}u.displayName="MDXCreateElement"},94:function(e,n,t){"use strict";var a=t(0),r=t.n(a),o=t(93),i=t(100),c=t(95),l=t.n(c);function s(){return(s=Object.assign||function(e){for(var n=1;n<arguments.length;n++){var t=arguments[n];for(var a in t)Object.prototype.hasOwnProperty.call(t,a)&&(e[a]=t[a])}return e}).apply(this,arguments)}n.a={code:function(e){var n=e.children;return"string"==typeof n?r.a.createElement(i.a,e):n},a:o.a,pre:function(e){return r.a.createElement("pre",s({className:l.a.mdxCodeBlock},e))}}},95:function(e,n,t){e.exports={mdxCodeBlock:"mdxCodeBlock_iHAB0Zg7"}}}]);