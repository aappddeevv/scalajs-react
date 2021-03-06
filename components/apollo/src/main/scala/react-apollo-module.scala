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

package react_apollo

import scala.scalajs.js

import js.annotation._
import js.|

import react._

import apollo_boost._
import apollo_client._
import graphql._

// @apollo/react-common, but pulls from ObservableQuery
@js.native
trait ObservableQueryFields[T, TVars <: js.Object] extends js.Object {
  // this looks quite complicated
  @JSName("fetchMore")
  val fetchMore
    : js.Function1[FetchMoreOptions[T, TVars] with FetchMoreQueryOptions[TVars], js.Promise[ApolloQueryResult[T]]] =
    js.native
  val variables: TVars = js.native

  // from pick ObservableQuery
  def refetch(variables: js.UndefOr[TVars] = js.undefined): js.Promise[ApolloQueryResult[T]] = js.native
  // subscribeToMore
  def updateQuery(f: js.Function2[T, UpdateQueryOptions[TVars], T]): Unit = js.native
  def stopPolling(): Unit = js.native
  def startPolling(pollInterval: Int): Unit = js.native
}

/** Is data undefined or null when there is no data?
 *  @see https://github.com/apollographql/react-apollo/pull/3388
 */
// @apollo/react-common
@js.native
trait QueryResult[T, TVars <: js.Object] extends ObservableQueryFields[T, TVars] {
  val client: apollo_client.ApolloClient = js.native
  val error: js.UndefOr[ApolloError] = js.native
  // is this null or undefined when it is not present?, this is different than ApolloQueryResult!
  // ts defs say T | undefined but not null !?!?
  //val data: T | Null = js.native

  // only these are returned if lazy query or skip=true, but the type lies.
  // if called is true, then I think the other attributes are there
  val data: js.UndefOr[T] = js.native
  val loading: Boolean = js.native
  val networkStatus: NetworkStatus = js.native
  val called: Boolean = js.native
}

object QueryResult {
  implicit final class RichQueryResult private[QueryResult] (private val qr: QueryResult[_, _]) extends AnyVal {
    def successfullyLoaded =
      !qr.loading &&
        qr.error.isEmpty &&
        qr.data.isDefined &&
        //qr.data != null &&
        qr.networkStatus == NetworkStatus.ready
    def finishedRemoteCall =
      !qr.loading && qr.networkStatus == NetworkStatus.ready
  }
}

trait ApolloClientOptionsMaker[T, TVars <: js.Object] {

  /** Make apollo_client.QueryOptions for the ApolloClient returned from `useQuery` which are slightly different than
   * `QueryHookOptions` used in the hook! This primarily exists so we can get a `js.Promise` to throw an exception
   * to the suspense mechanism.
   */
  def makeClientOptions(
    query: DocumentNode,
    variables: js.UndefOr[TVars] = js.undefined,
    unsafeVariables: js.UndefOr[js.Dynamic] = js.undefined,
    errorPolicy: js.UndefOr[ErrorPolicy] = js.undefined,
    context: js.UndefOr[js.Object] = js.undefined,
    fetchResults: js.UndefOr[Boolean] = js.undefined,
    metadata: js.UndefOr[js.Object] = js.undefined,
    fetchPolicy: js.UndefOr[FetchPolicy] = js.undefined
  ) = {
    val __obj = js.Dynamic
      .literal(
        "query" -> query,
        "errorPolicy" -> errorPolicy,
        "context" -> context,
        "fetchResult" -> fetchResults,
        "metadata" -> metadata,
        "fetchPolicy" -> fetchPolicy
      )
    if (variables.isDefined) __obj.updateDynamic("variables")(variables)
    if (unsafeVariables.isDefined) __obj.updateDynamic("variables")(unsafeVariables)
    __obj.asInstanceOf[apollo_client.QueryOptions[TVars]]
  }
}

trait OptionsMaker[T, TVars <: js.Object] extends ApolloClientOptionsMaker[T, TVars] {

  /** Make QueryHookOptions */
  def makeOptions(
    variables: js.UndefOr[TVars] = js.undefined,
    unsafeVariables: js.UndefOr[js.Dynamic] = js.undefined,
    query: js.UndefOr[DocumentNode] = js.undefined,
    displayName: js.UndefOr[String] = js.undefined,
    onCompleted: js.UndefOr[js.Function1[js.UndefOr[T], Unit]] = js.undefined,
    onError: js.UndefOr[js.Function1[ApolloError, Unit]] = js.undefined,
    ssr: js.UndefOr[Boolean] = js.undefined,
    fetchPolicy: js.UndefOr[WatchQueryFetchPolicy] = js.undefined,
    errorPolicy: js.UndefOr[ErrorPolicy] = js.undefined,
    pollInterval: js.UndefOr[Int] = js.undefined,
    client: js.UndefOr[ApolloClient] = js.undefined,
    notifyOnNetworkStatusChange: js.UndefOr[Boolean] = js.undefined,
    context: js.UndefOr[js.Object] = js.undefined,
    partialRefetch: js.UndefOr[Boolean] = js.undefined,
    returnPartialData: js.UndefOr[Boolean] = js.undefined,
    skip: js.UndefOr[Boolean] = js.undefined
  ) = {
    val __obj = js.Dynamic
      .literal(
        "query" -> query,
        "displayName" -> displayName,
        "onCompleted" -> onCompleted,
        "onError" -> onError,
        "ssr" -> ssr,
        "fetchPolicy" -> fetchPolicy,
        "errorPolicy" -> errorPolicy,
        "pollInterval" -> pollInterval,
        "client" -> client,
        "notifyOnNetworkStatusChange" -> notifyOnNetworkStatusChange,
        "context" -> context,
        "partialRefetch" -> partialRefetch,
        "returnPartialData" -> returnPartialData,
        "skip" -> skip,
      )
    if (variables.isDefined) __obj.updateDynamic("variables")(variables)
    if (unsafeVariables.isDefined) __obj.updateDyanmic("variables")(unsafeVariables)
    __obj.asInstanceOf[QueryHookOptions[T, TVars]]
  }

}

/** Because there are some duplicative types in the signatures
 * which become burdensome, this helper class reduces type noise.
 * Instantiate the object then use values and methods in it without
 * need to always specify the types.
 */
case class UseQuery[T, TVars <: js.Object]() extends OptionsMaker[T, TVars] {
  type TArg = T
  type TVarsArg = TVars
  def useQuery(query: DocumentNode, options: js.UndefOr[QueryHookOptions[T, TVars] | js.Dynamic] = js.undefined) =
    module.useQuery[T, TVars](query, options)
}

/** Instantiate to avoid all the type parameter noise. */
case class UseLazyQuery[T, TVars <: js.Object]() extends ApolloClientOptionsMaker[T, TVars] {
  type TArg = T
  type TVarsArg = TVars

  def useLazyQuery(query: DocumentNode, options: js.UndefOr[LazyQueryHookOptions[T, TVars] | js.Dynamic] = js.undefined)
    : (js.Function1[QueryLazyOptions[TVars], Unit], QueryResult[T, TVars]) =
    module.useLazyQuery[T, TVars](query, options)

  /** Make LazyQueryHookOptions for the hook, not for the lazy fetcher. */
  def makeOptions(
    variables: js.UndefOr[TVars] = js.undefined,
    unsafeVariables: js.UndefOr[js.Dynamic] = js.undefined,
    query: js.UndefOr[DocumentNode] = js.undefined,
    displayName: js.UndefOr[String] = js.undefined,
    onCompleted: js.UndefOr[js.Function1[js.UndefOr[T], Unit]] = js.undefined,
    onError: js.UndefOr[js.Function1[ApolloError, Unit]] = js.undefined,
    ssr: js.UndefOr[Boolean] = js.undefined,
    fetchPolicy: js.UndefOr[WatchQueryFetchPolicy] = js.undefined,
    errorPolicy: js.UndefOr[ErrorPolicy] = js.undefined,
    pollInterval: js.UndefOr[Int] = js.undefined,
    client: js.UndefOr[ApolloClient] = js.undefined,
    notifyOnNetworkStatusChange: js.UndefOr[Boolean] = js.undefined,
    context: js.UndefOr[js.Object] = js.undefined,
    partialRefetch: js.UndefOr[Boolean] = js.undefined,
    returnPartialData: js.UndefOr[Boolean] = js.undefined,
    skip: js.UndefOr[Boolean] = js.undefined
  ) = {
    val __obj = js.Dynamic
      .literal(
        "query" -> query,
        "displayName" -> displayName,
        "onCompleted" -> onCompleted,
        "onError" -> onError,
        "ssr" -> ssr,
        "fetchPolicy" -> fetchPolicy,
        "errorPolicy" -> errorPolicy,
        "pollInterval" -> pollInterval,
        "client" -> client,
        "notifyOnNetworkStatusChange" -> notifyOnNetworkStatusChange,
        "context" -> context,
        "partialRefetch" -> partialRefetch,
        "returnPartialData" -> returnPartialData,
        "skip" -> skip,
      )
    if (variables.isDefined) __obj.updateDynamic("variables")(variables)
    if (unsafeVariables.isDefined) __obj.updateDyanmic("variables")(unsafeVariables)
    __obj.asInstanceOf[LazyQueryHookOptions[T, TVars]]
  }

  /** Make LazyQueryHookOptions */
  def makeLazyOptions(
    variables: js.UndefOr[TVars] = js.undefined,
    unsafeVariables: js.UndefOr[js.Dynamic] = js.undefined,
    context: js.UndefOr[js.Object] = js.undefined,
  ) = {
    val __obj = js.Dynamic
      .literal(
        "context" -> context,
      )
    if (variables.isDefined) __obj.updateDynamic("variables")(variables)
    if (unsafeVariables.isDefined) __obj.updateDyanmic("variables")(unsafeVariables)
    __obj.asInstanceOf[QueryLazyOptions[TVars]]
  }
}

@js.native
@JSImport("react-apollo", JSImport.Namespace)
private[react_apollo] object module extends js.Object {
  // ... from @apollo/react-hooks
  def useQuery[T, TVars <: js.Object](
    query: DocumentNode,
    options: js.UndefOr[QueryHookOptions[T, TVars] | js.Dynamic] = js.undefined
  ): QueryResult[T, TVars] = js.native

  def useLazyQuery[T, TVars <: js.Object](
    query: DocumentNode,
    options: js.UndefOr[LazyQueryHookOptions[T, TVars] | js.Dynamic] = js.undefined
  ): js.Tuple2[js.Function1[QueryLazyOptions[TVars], Unit], QueryResult[T, TVars]] = js.native

  // Need Ext for ExecutionResult...
  def useMutation[T, TVars <: js.Object](
    mutation: DocumentNode,
    options: js.UndefOr[MutationHookOptions[T, TVars] | js.Dynamic] = js.undefined
  ): js.Tuple2[
    js.Function1[MutationFunctionOptions[T, TVars], js.Promise[ExecutionResult[T]]],
    MutationResult[T]
  ] = js.native

  def useSubscription[T, TVars <: js.Object](
    subscription: DocumentNode,
    options: js.UndefOr[SubscriptionHookOptions[T, TVars]] = js.undefined
  ): SubscriptionResult[T] with Variables[TVars] = js.native

  def useBaseQuery[T, TVars <: js.Object](
    query: DocumentNode,
    options: js.UndefOr[QueryHookOptions[T, TVars]] = js.undefined
  ): js.Tuple2[js.Function1[js.UndefOr[QueryLazyOptions[TVars]], Unit], QueryResult[T, TVars]] = js.native

  def useApolloClient(): apollo_client.ApolloClient = js.native

  // @apollo/react-common
  def getApolloContext(): ReactContext[ApolloContextValue] = js.native
  // @apollo/react-common
  def resetApolloContext(): Unit = js.native
}

// @apollo/react-common
trait ApolloContextValue extends js.Object {
  var client: js.UndefOr[ApolloClient] = js.undefined
  var renderPromises: js.UndefOr[js.Object] = js.undefined
}

trait BaseQueryOptions[TVars <: js.Object] extends js.Object {
  var ssr: js.UndefOr[Boolean] = js.undefined
  var variables: js.UndefOr[TVars | js.Dynamic] = js.undefined
  var fetchPolicy: js.UndefOr[WatchQueryFetchPolicy] = js.undefined
  var errorPolicy: js.UndefOr[ErrorPolicy] = js.undefined
  var pollInterval: js.UndefOr[Int] = js.undefined
  var client: js.UndefOr[ApolloClient] = js.undefined
  var notifyOnNetworkStatusChange: js.UndefOr[Boolean] = js.undefined
  var context: js.UndefOr[js.Object] = js.undefined
  var partialRefetch: js.UndefOr[Boolean] = js.undefined
  var returnPartialData: js.UndefOr[Boolean] = js.undefined
}

// @apollo/react-common
trait Skip extends js.Object {
  var skip: js.UndefOr[Boolean] = js.undefined
}

// @apollo/react-common
trait QueryFunctionOptions[T, TVars <: js.Object] extends BaseQueryOptions[TVars] {
  var displayName: js.UndefOr[String] = js.undefined
  var onCompleted: js.UndefOr[js.Function1[js.UndefOr[T], Unit]] = js.undefined
  var onError: js.UndefOr[js.Function1[ApolloError, Unit]] = js.undefined
}

// @apollo/react-hooks
trait QueryHookOptions[T, TVars <: js.Object] extends QueryFunctionOptions[T, TVars] with Skip {
  // children?
  var query: js.UndefOr[DocumentNode] = js.undefined
}

// @apollo/react-hooks
object QueryHookOptions {
  def variables[T, TVars <: js.Object](v: TVars) = new QueryHookOptions[T, TVars] {
    variables = v
  }
  def unsafeVariables[T, TVars <: js.Object](v: js.Dynamic) = new QueryHookOptions[T, TVars] {
    variables = v
  }
}

// @apollo/react-hooks, return value from useLazyQuery
@js.native
trait QueryLazyOptions[TVars <: js.Object] extends js.Object {
  var variables: js.UndefOr[TVars] = js.undefined
  // there is a type call Context = Record<string, any>
  var context: js.UndefOr[js.Object] = js.undefined
}

// @apollo/react-hooks
trait LazyQueryHookOptions[T, TVars <: js.Object] extends QueryFunctionOptions[T, TVars] {
  var query: js.UndefOr[DocumentNode] = js.undefined
}

// @apollo/react-common
object ApolloProvider {
  @js.native
  @JSImport("react-apollo", "ApolloProvider")
  object JS extends ReactJSComponent

  trait Props extends ApolloContextValue

  def apply(props: Props)(children: ReactNode*) =
    react.createElementN(JS, props)(children: _*)

  def apply(c: apollo_client.ApolloClient)(children: ReactNode*) =
    react.createElementN(JS, new Props { client = c })(children: _*)
}

// @apollo/react-common
trait BaseMutationOptions[T, TVars <: js.Object] extends js.Object {
  var variables: js.UndefOr[TVars] = js.undefined
  //optimisticResponse
  @JSName("refetchQueries")
  var refetchQueriesByName: js.UndefOr[js.Array[String]] = js.undefined
  // this is not quite right, how to do varargs js funciton decl?
  @JSName("refetchQueries")
  var refetchQueriesBy: js.UndefOr[js.Function1[js.Array[js.Any], js.Array[String]]] = js.undefined
  var awaitRefetchQueries: js.UndefOr[Boolean] = js.undefined
  var errorPolicy: js.UndefOr[ErrorPolicy] = js.undefined
  var update: js.UndefOr[MutationUpdaterFn[T]] = js.undefined
  var client: js.UndefOr[ApolloClient] = js.undefined
  var notifyOnNetworkStatusChange: js.UndefOr[Boolean] = js.undefined
  var context: js.UndefOr[js.Object] = js.undefined
  var onCompleted: js.UndefOr[js.Function1[js.UndefOr[T], Unit]] = js.undefined
  var onError: js.UndefOr[js.Function1[ApolloError, Unit]] = js.undefined
  var fetchPolicy: js.UndefOr[WatchQueryFetchPolicy] = js.undefined
  var ignoreResults: js.UndefOr[Boolean] = js.undefined
}

// @apollo/react-common
trait MutationFunctionOptions[T, TVars <: js.Object] extends js.Object {
  var variables: js.UndefOr[TVars] = js.undefined
  @JSName("optimisticResponse")
  var optimisticResponseStrict: js.UndefOr[T] = js.undefined
  @JSName("optimisticResponse")
  var optimisticResponse: js.UndefOr[js.Function1[TVars, T]] = js.undefined
  @JSName("refetchQueries")
  var refetchQueriesByName: js.UndefOr[js.Array[String]] = js.undefined
  var awaitRefetchQueries: js.UndefOr[Boolean] = js.undefined
  var update: js.UndefOr[MutationUpdaterFn[T]] = js.undefined
  var context: js.UndefOr[js.Object] = js.undefined
  var fetchPolicy: js.UndefOr[WatchQueryFetchPolicy] = js.undefined
}

object MutationFunctionOptions {
  def forVariables[T, TVars <: js.Object](values: TVars) =
    new MutationFunctionOptions[T, TVars] {
      variables = values
    }
}

// @apollo/react-hooks
trait MutationHookOptions[T, TVars <: js.Object] extends BaseMutationOptions[T, TVars] {
  var mutation: js.UndefOr[DocumentNode] = js.undefined
}

/** Because there are some duplicative types in the signatures
 * which become burdensome, this helper class reduces type noise.
 * Instantiate the object then use values and methods in it without
 * need to always specify the types.
 */
case class UseMutation[T, TVars <: js.Object]() {
  type TArg = T
  type TVarsArg = TVars

  def useMutation(
    mutation: DocumentNode,
    options: js.UndefOr[MutationHookOptions[T, TVars] | js.Dynamic] = js.undefined) =
    react_apollo.useMutation[T, TVars](mutation, options)

  /** Make MutationHookOptions */
  def makeOptions(
    // refetchQueries ???
    awaitRefetchQueries: js.UndefOr[Boolean] = js.undefined,
    client: js.UndefOr[ApolloClient] = js.undefined,
    context: js.UndefOr[js.Object] = js.undefined,
    errorPolicy: js.UndefOr[ErrorPolicy] = js.undefined,
    fetchPolicy: js.UndefOr[WatchQueryFetchPolicy] = js.undefined,
    ignoreResults: js.UndefOr[Boolean] = js.undefined,
    mutation: js.UndefOr[DocumentNode] = js.undefined,
    notifyOnNetworkStatusChange: js.UndefOr[Boolean] = js.undefined,
    onCompleted: js.UndefOr[js.Function1[js.UndefOr[T], Unit]] = js.undefined,
    onError: js.UndefOr[js.Function1[ApolloError, Unit]] = js.undefined,
    update: js.UndefOr[MutationUpdaterFn[T]] = js.undefined,
    variables: js.UndefOr[TVars] = js.undefined,
    unsafeVariables: js.UndefOr[js.Dynamic] = js.undefined,
  ) = {
    val __obj = js.Dynamic
      .literal(
        "awaitRefetchQueries" -> awaitRefetchQueries,
        "client" -> client,
        "context" -> context,
        "errorPolicy" -> errorPolicy,
        "fetchPolicy" -> fetchPolicy,
        "igoreResults" -> ignoreResults,
        "mutation" -> mutation,
        "notifyOnNetworkStatusChange" -> notifyOnNetworkStatusChange,
        "onCompleted" -> onCompleted,
        "onError" -> onError,
        "update" -> update,
        //"variables" -> variables.asInstanceOf[js.Any],
      )
    if (variables.isDefined) __obj.updateDynamic("variables")(variables)
    if (unsafeVariables.isDefined) __obj.updateDyanmic("variables")(unsafeVariables)
    __obj.asInstanceOf[MutationHookOptions[T, TVars]]
  }

  /** Options needed for the "caller" function return from the mutation hook.
   * Other config info, such as error policy and the operation itself are take
   * from the hook.
   */
  def makeFunctionOptions(
    unsafeVariables: js.UndefOr[js.Dynamic] = js.undefined,
    variables: js.UndefOr[TVars] = js.undefined,
    optimisticResponseStrict: js.UndefOr[T] = js.undefined,
    optimisticResponse: js.UndefOr[js.Function1[TVars, T]] = js.undefined,
    refetchQueriesByName: js.UndefOr[js.Array[String]] = js.undefined,
    awaitRefetchQueries: js.UndefOr[Boolean] = js.undefined,
    update: js.UndefOr[MutationUpdaterFn[T]] = js.undefined,
    context: js.UndefOr[js.Object] = js.undefined,
    fetchPolicy: js.UndefOr[WatchQueryFetchPolicy] = js.undefined,
  ) = {
    val __obj = js.Dynamic
      .literal(
        "context" -> context,
        "fetchPolicy" -> fetchPolicy,
        "update" -> update,
        "refetchQueries" -> refetchQueriesByName,
        "awaitRefetchQueries" -> awaitRefetchQueries,
      )
    if (optimisticResponseStrict.isDefined)
      __obj.updateDynamic("optimisticResponse")(optimisticResponseStrict.asInstanceOf[js.Any])
    if (optimisticResponse.isDefined) __obj.updateDynamic("optimisticResponse")(optimisticResponse)
    if (variables.isDefined) __obj.updateDynamic("variables")(variables)
    if (unsafeVariables.isDefined) __obj.updateDyanmic("variables")(unsafeVariables)
    __obj.asInstanceOf[MutationFunctionOptions[T, TVars]]
  }

  /** Make apollo_client.QueryOptions for the ApolloClient returned from `useMutation` which are slightly different than
   * `MutationHookOptions` used in the hook!
   * This primarily exists so we can get a `js.Promise` to throw an exception
   * to the suspense mechanism.
   */
  def makeClientOptions(
    mutation: DocumentNode,
    context: js.UndefOr[js.Object] = js.undefined,
    fetchPolicy: js.UndefOr[FetchPolicy] = js.undefined,
    errorPolicy: js.UndefOr[ErrorPolicy] = js.undefined,
    variables: js.UndefOr[TVars] = js.undefined,
    unsafeVariables: js.UndefOr[js.Dynamic] = js.undefined,
    update: js.UndefOr[MutationUpdaterFn[T]] = js.undefined,
    awaitRefetchQueries: js.UndefOr[Boolean] = js.undefined,
    updateQueries: js.UndefOr[MutationQueryReducersMap[T, js.Object]] = js.undefined,
    optimisticResponse: js.UndefOr[T | js.Function1[TVars, T]] = js.undefined,
  ) = {
    val __obj = js.Dynamic
      .literal(
        "mutation" -> mutation,
        "context" -> context,
        "fetchPolicy" -> fetchPolicy,
        "errorPolicy" -> errorPolicy,
        //"variables" -> variables.asInstanceOf[js.Any],
        "update" -> update,
        "awaitRefetchQueries" -> awaitRefetchQueries,
        "updateQueries" -> updateQueries,
        "optimisticResponse" -> optimisticResponse.asInstanceOf[js.Any],
      )
    if (variables.isDefined) __obj.updateDynamic("variables")(variables)
    if (unsafeVariables.isDefined) __obj.updateDyanmic("variables")(unsafeVariables)
    __obj.asInstanceOf[apollo_client.MutationOptions[T, TVars]]
  }
}

// @apollo/react-common
@js.native
trait MutationResult[T] extends js.Object {
  val loading: Boolean = js.native
  val called: Boolean = js.native
  val client: js.UndefOr[ApolloClient] = js.native

  val data: js.UndefOr[T] = js.native
  val error: js.UndefOr[ApolloError] = js.native
}

object MutationResult {
  implicit final class RichMutationResult[T] private[MutationResult] (private val mr: MutationResult[T])
      extends AnyVal {
    def successful = !mr.loading && mr.called && mr.error.isEmpty
    def notSuccessful = !successful
  }
}

// @apollo/react-common
@js.native
trait SubscriptionResult[T] extends js.Object {
  val loading: Boolean = js.native
  val data: js.UndefOr[T] = js.native
  val error: js.UndefOr[ApolloError] = js.native
}

@js.native
trait Variables[TVars <: js.Object] extends js.Object {
  val variables: js.UndefOr[TVars] = js.undefined
}

// @apollo/react-common
@js.native
trait OnSubscriptionDataOptions[T] extends js.Object {
  val client: ApolloClient = js.native
  val subscriptionData: SubscriptionResult[T]
}

// @apollo/react-common
trait BaseSubscriptionOptions[T, TVars <: js.Object] extends js.Object {
  var variables: js.UndefOr[TVars] = js.undefined
  var fetchPolicy: js.UndefOr[WatchQueryFetchPolicy] = js.undefined
  @JSName("shouldResubscribe")
  var shouldResubscribeStrict: js.UndefOr[Boolean] = js.undefined
  var shouldResubscribe: js.UndefOr[js.Function1[BaseSubscriptionOptions[T, TVars], Boolean]] = js.undefined
  var client: js.UndefOr[ApolloClient] = js.undefined
  var skip: js.UndefOr[Boolean] = js.undefined
  var onSubscriptionData: js.UndefOr[js.Function1[OnSubscriptionDataOptions[T], js.Any]] = js.undefined
  var onSubscriptionComplete: js.UndefOr[js.Function0[Unit]] = js.undefined
}

// @apollo/react-hooks
trait SubscriptionHookOptions[T, TVars <: js.Object] extends BaseSubscriptionOptions[T, TVars] {
  val subscription: js.UndefOr[DocumentNode] = js.undefined
  //var children: js.UndefOr[js.Function1[SubscriptionResult[T], ReactNode|Null]] = js.undefined
}

// @apollo/react-hooks this is actually a class for ssr use
@js.native
@JSImport("@apollo/react-hooks", "RenderPromises")
class RenderPromises() extends js.Object {
  // add "registration" functions ...
  def hasPromises(): Boolean = js.native
  def consumeAndAwaitPromises(): js.Promise[js.Array[js.Any]] = js.native
}
