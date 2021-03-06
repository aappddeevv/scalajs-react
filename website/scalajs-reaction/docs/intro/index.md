---
id: index
title: What is it?
---

The scalajs-reaction website is a WIP.

API docs and the demo are not hooked up yet :-) You can generate docs
and run the demo by cloning the repo and running them directly yourself.

# A scala.js facade

scalajs-reaction is a scala.js facade over reactjs that provides a functional
API in the spirit of ReasonReact. It focuses on integration with existing
javascript-based react applications so you can plug in scalajs-react components
to any part of the component hierarchy.

A scalajs-reaction component is just a javascript function and you can easily
create that using standard scala.js code. Nothing new is invented in this
facade.

The library is designed to create components that can be used and consumed by
other react components in javascript and scala seamlessly.

The library is also designed to be a thin layer over react and not provide
abstractions that you may or may not want. Abstractions have costs
and when it comes to running in the browser, I found that it was better
to have a react library with lower overhead and build the abstractions
at much higher levels in the application. Accordingly, the facades
favor simple scala.js interfaces into the javascript side of the react
world. Abstract your own concepts on top with assurance that this
library adds very little overhead.

This library does provide a few optional macros for working with scala.js
traits. I spend most of my time writing
facades for people who want to use scalajs-reaction so you'll see
there is a wide variety of facades available including support
for react-native. You can also use it on electron easily.

Since the project is kept very simple, the scalajs-reaction ecosystem takes very little
effort to maintain.

# ReasonReact

The interface is built on the model provided by
[ReasonReact](https://reasonml.github.io/reason-react). You can apply the same
concepts in that library to this API.

The ReasonReact approach is to provide robust interfaces to hooks. scalajs-reaction
follows this model is 100% hooks focused. It does not provide any facade
support for class based components. Anyway, you should be using hooks.

# API Documentation

- [API: all modules](../api)

The API focuses on integration with existing javascript libraries.
For example, it is easy to interface with redux and it allows
you to connect your component to an existing redux store.
More importantly, it allows you to interface with the great
set of reactjs widget libraries.

The API link does not include some of the individual projects that provide
facades for additional functionality but it provides a significant amount of the
core facades. In other words, there is more here so check the source!

# Demo

A WIP demo is here:
[Demo](../demo)
