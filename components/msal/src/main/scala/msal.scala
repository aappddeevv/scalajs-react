/*
 * Copyright (c) 2018 The Trapelo Group
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package azure
package msal

import scala.scalajs.js
import js.Dynamic.literal

import js.annotation._
import js.|

trait AuthenticationParameters extends js.Object {
  var scopes: js.UndefOr[js.Array[String]] = js.undefined
  var extraScopesToConsent: js.UndefOr[js.Array[String]] = js.undefined
  var prompt: js.UndefOr[String] = js.undefined
  var extraQueryParameters: js.UndefOr[StringDict] = js.undefined

  /** https://docs.microsoft.com/en-us/azure/active-directory/develop/scenario-spa-acquire-token */
  var claimsRequest: js.UndefOr[String] = js.undefined
  var authority: js.UndefOr[String] = js.undefined
  var state: js.UndefOr[String] = js.undefined
  var correlationId: js.UndefOr[String] = js.undefined
  var account: js.UndefOr[Account] = js.undefined
  var sid: js.UndefOr[String] = js.undefined
  var loginHint: js.UndefOr[String] = js.undefined
  var forceRefresh: js.UndefOr[Boolean] = js.undefined
  var redirectUri: js.UndefOr[String] = js.undefined
}

@js.native
@JSImport("@azure/authority", "Authority")
abstract class Authority(authority: String, validateAuthority: Boolean) extends js.Object {}

@js.native
sealed trait AuthorityType extends js.Any
object AuthorityType {
  val Aad = 0.asInstanceOf[AuthorityType]
  val Adfs = 0.asInstanceOf[AuthorityType]
  val B2C = 0.asInstanceOf[AuthorityType]
}

@js.native
@JSImport("@azure/msal", "AuthError")
class AuthError(val errorCode: String, val errorMessage: js.UndefOr[String] = js.undefined)
    extends js.Error(errorMessage.getOrElse("<no message>"))

@js.native
@JSImport("@azure/msal", "AuthError")
object AuthError extends js.Object {
  def createUnexpectedError(desc: String): AuthError = js.native
  def createNoWindowObjectError(desc: String): AuthError = js.native
}

@js.native
@JSImport("@azure/msal", "ClientAuthError")
class ClientAuthError(code: String, message: js.UndefOr[String] = js.undefined) extends AuthError(code, message)

@js.native
@JSImport("@azure/msal", "ClientAuthError")
object ClientAuthError extends js.Object {
  // add static create functions...
}

@js.native
@JSImport("@azure/msal", "ClientConfigurationError")
class ClientConfigurationError(code: String, message: js.UndefOr[String] = js.undefined)
    extends ClientAuthError(code, message)

@js.native
@JSImport("@azure/msal", "ClientConfigurationError")
object ClientConfigurationError extends js.Object {
  // static creators...
}

@js.native
@JSImport("@azure/msal", "ServerError")
class ServerError(code: String, message: js.UndefOr[String] = js.undefined) extends AuthError(code, message)

@js.native
@JSImport("@azure/msal", "ServerError")
object ServerError extends js.Object {
  // static creators...
}

@js.native
@JSImport("@azure/msal", "InteractionRequiredAuthError")
class InteractionRequiredAuthError(code: String, message: js.UndefOr[String] = js.undefined)
    extends ServerError(code, message)

@js.native
@JSImport("@azure/msal", "InteractionRequiredAuthError")
object InteractionRequiredAuthError extends js.Object {
  // static creators...
}

/** This is really a class, but I do not need to create one so make it a struct. */
@js.native
trait Account extends js.Object {
  val accountIdentifier: String
  val homeAccountIdentifier: String
  val userName: String
  val name: String
  val idToken: StringDict
  val idTokenClaims: StringDict
  val sid: String
  val environment: String
}

@js.native
@JSImport("@azure/msal", "Account")
object Account extends js.Object {
  def compareAccounts(a1: Account, a2: Account): Boolean = js.native
}

/** Really a class but we make a non-native js trait. */
@js.native
trait IdToken extends js.Object {
  val issue: String
  val objectId: String
  val subject: String
  val tenantId: String
  val version: String
  val preferredName: String
  val name: String
  val homeObjectId: String
  val nonce: String
  val expiration: String
  val rawIdToken: String
  val claims: StringDict
  val sid: String
  val cloudInstance: String
}

@js.native
trait AuthResponse extends js.Object {
  val uniqueId: String = js.native
  val tenantId: String = js.native
  val tokenType: String = js.native
  val idToken: IdToken = js.native
  val idTokenClaims: StringDict = js.native
  val accessToken: String = js.native
  val scope: js.Array[String] = js.native
  val expiresOn: js.Date = js.native
  val account: Account = js.native
  val accountState: String = js.native
  val fromCache: Boolean = js.native
}

trait AuthOptions extends js.Object {
  val clientId: String
  var authority: js.UndefOr[String] = js.undefined
  var validateAuthority: js.UndefOr[Boolean] = js.undefined
  var redirectUri: js.UndefOr[String | js.Function1[Unit, String]] = js.undefined
  var postLogoutRedirectUri: js.UndefOr[String | js.Function1[Unit, String]] = js.undefined
  var navigateToLoginRequestUrl: js.UndefOr[Boolean] = js.undefined
}

trait CacheOptions extends js.Object {
  var cacheLocation: js.UndefOr[CacheLocation] = js.undefined
  var storeAuthStateInCookie: js.UndefOr[Boolean] = js.undefined
}

/* not in public API?
trait TelemetryPlatform extends js.Object {
  val sdk: String
  val sdkVersion: String
  val applicationName: String
  val applicationVresion: String
}

trait TelemetryConfig extends js.Object {
  var platform: js.UndefOr[TelemetryPlatform]       = js.undefined
  var onlySendFailureTelemetry: js.UndefOr[Boolean] = js.undefined
  val clientId: String
}
 */

trait TelemetryOptions extends js.Object {
  var applicationName: js.UndefOr[String] = js.undefined
  var applicationVersion: js.UndefOr[String] = js.undefined
  var telemetryEmitter: js.UndefOr[TelemetryEmitter] = js.undefined
}

trait SystemOptions extends js.Object {
  var logger: js.UndefOr[Logger] = js.undefined
  var loadFrameTimeout: js.UndefOr[Int] = js.undefined
  var tokenRenewalOffsetSeconds: js.UndefOr[Int] = js.undefined
  var telemetry: js.UndefOr[TelemetryOptions] = js.undefined
}

trait FrameworkOptions extends js.Object {
  var isAngular: js.UndefOr[Boolean] = js.undefined
  var unprotectedResources: js.UndefOr[js.Array[String]] = js.undefined
  var protectedResourceMap: js.UndefOr[js.Dictionary[js.Array[String]] | js.Dynamic | js.Object] =
    js.undefined
}

trait LoggerOptions extends js.Object {
  var correlationId: js.UndefOr[String] = js.undefined
  var level: js.UndefOr[LogLevel] = js.undefined
  var piiLoggingEnabled: js.UndefOr[Boolean] = js.undefined
}

@js.native
sealed trait LogLevel extends js.Any
object LogLevel {
  val Error = 0.asInstanceOf[LogLevel]
  val Warning = 1.asInstanceOf[LogLevel]
  val Info = 2.asInstanceOf[LogLevel]
  val Verbose = 3.asInstanceOf[LogLevel]
}

@js.native
@JSImport("@azure/msal", "Logger")
class Logger(cb: LoggerCallback, options: js.UndefOr[LoggerOptions] = js.undefined) extends js.Object {
  val isPiiLoggingEnabled: Boolean = js.native
}

@js.native
sealed trait CacheLocation extends js.Any
object CacheLocation {
  val sessionStorage = "sessionStorage".asInstanceOf[CacheLocation]
  val localStorage = "localStorage".asInstanceOf[CacheLocation]
}

trait ConfigurationBase extends js.Object {
  var cache: js.UndefOr[CacheOptions] = js.undefined
  var system: js.UndefOr[SystemOptions] = js.undefined
  var framework: js.UndefOr[FrameworkOptions] = js.undefined
}

trait ConfigurationInit extends ConfigurationBase {
  var auth: js.UndefOr[AuthOptions] = js.undefined
}

object ConfigurationInit {
  private implicit class RichInit private[ConfigurationInit] (private val c: ConfigurationInit) extends AnyVal {
    def withRequired(auth: AuthOptions) =
      js.Object.assign(literal(), c, literal("auth" -> auth)).asInstanceOf[Configuration]
    def hasRequired = c.asInstanceOf[Configuration]
  }
}

trait Configuration extends ConfigurationBase {
  val auth: AuthOptions
}

@js.native
@JSImport("@azure/msal", "UserAgentApplication")
class UserAgentApplication(configuration: Configuration) extends js.Object {

  val authority: String = js.native
  def getAuthorityInstance(): Authority = js.native

  def loginPopup(
    request: js.UndefOr[AuthenticationParameters] = js.undefined
  ): js.Promise[AuthResponse] = js.native
  def loginRedirect(request: js.UndefOr[AuthenticationParameters] = js.undefined): Unit = js.native

  def acquireTokenRedirect(request: AuthenticationParameters): Unit = js.native
  def acquireTokenPopup(request: AuthenticationParameters): js.Promise[AuthResponse] = js.native
  def acquireTokenSilent(request: AuthenticationParameters): js.Promise[AuthResponse] = js.native

  def handleRedirectCallback(tcb: TokenReceivedCallback, ecb: ErrorReceivedCallback): Unit =
    js.native
  def handleRedirectCallback(acb: AuthResponseCallback): Unit = js.native

  def logout(): Unit = js.native
  def getAccount(): Account = js.native
  def getAccountState(state: String): String = js.native
  def getAllAccounts(): js.Array[Account] = js.native
  def getLoginInProgress(): Boolean = js.native
  def getRedirectUri(reqRedirectUri: js.UndefOr[String]): String = js.native
  def getPostLogoutRedirectUri(): String = js.native
  def getCurrentConfiguration(): Configuration = js.native

}

@js.native
@JSImport("@azure/msal/utils", "CryptoUtils")
object CryptoUtils extends js.Object {
  def createNewGuid(): String = js.native
  def isGuid(guid: String): Boolean = js.native
  def decimalToHex(num: Int): String = js.native
  def base64Encode(input: String): String = js.native
  def base64Decode(inut: String): String = js.native
  def deserialize(query: String): js.Any = js.native
}

@js.native
@JSImport("@azure/msal/utils", "TimeUtils")
object TimeUtils extends js.Object {
  def parseExpiresIn(expiresIn: String): Double = js.native
  def now(): Double = js.native
}

@js.native
@JSImport("@azure/msal/utils", "TimeUtils")
object TokenUtils extends js.Object {
  def decodeJwt(jwtToken: String): js.Any = js.native
  def extractIdToken(encodedIdToken: String): js.Any = js.native
}

@js.native
@JSImport("@azure/msal", "Configuration")
object Configuration extends js.Object {

  /** Use module fallback defaults. */
  def buildConfiguration(configuration: ConfigurationInit): Configuration = js.native
}
