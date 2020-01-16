// Copyright (c) 2019 The Trapelo Group LLC
// This software is licensed under the MIT License (MIT).
// For more information see LICENSE or https://opensource.org/licenses/MIT

package react_router

import scala.scalajs.js
import js.annotation._

package object dom {
  type Pathname = String
  type Search = String
  type Path = String
  type Hash = String
  type Href = String
  type LocationKey = String

  /** LocationDescriptor is Location but all vars are optional. */
  implicit class Location2LocationDescriptor[S](location: Location[S]) {
    def toLocationDescriptor: LocationDescriptor[S] = location.asInstanceOf[LocationDescriptor[S]]
  }
}
