// Copyright (c) 2018 The Trapelo Group LLC
// This software is licensed under the MIT License (MIT).
// For more information see LICENSE or https://opensource.org/licenses/MIT

// Copyright (c) 2018 The Trapelo Group LLC
// This software is licensed under the MIT License (MIT).
// For more information see LICENSE or https://opensource.org/licenses/MIT
package fabric

import scala.scalajs.js
import js.annotation._

@js.native
@JSImport("@uifabric/merge-styles", JSImport.Namespace)
@deprecated("Use merge_styles")
object MergeStyles extends styling.MergeStyles

@js.native
@JSImport("@uifabric/merge-styles", JSImport.Namespace)
object merge_styles extends styling.MergeStyles