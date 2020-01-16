// Copyright (c) 2019 The Trapelo Group LLC
// This software is licensed under the MIT License (MIT).
// For more information see LICENSE or https://opensource.org/licenses/MIT

package ttg 
package forms

// trait SetTouchModelPart {
//   self: FormControllerBase =>

//   object MapTouchModel extends TouchModelLike {
//     type Touches = collection.immutable.Set[String]
//     val EmptyTouches = collection.immutable.Set[String]()
//     def touch(id: String, touches: Touches): Touches =
//       touches + id
//     def touched(id: String, touches: Touches): Boolean =
//       touches.contains(id)
//   }

//   type TouchModel = MapTouchModel.type
//   val touches = MapTouchModel
// }


trait MapTouches extends HasTouches.Service {
  type Touches = collection.immutable.Set[String]
  val EmptyTouches = collection.immutable.Set[String]()
  def touch(id: String, touches: Touches): Touches =
    touches + id
  def touched(id: String, touches: Touches): Boolean =
    touches.contains(id)
}

object MapTouches extends MapTouches
