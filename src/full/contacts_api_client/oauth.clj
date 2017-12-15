(ns full.contacts-api-client.oauth
  (:require [full.contacts-api-client.http :as http]
            [full.contacts-api-client.util :as util]
            [ring.util.codec :refer [form-encode]]))


(defn get-authorize-url
  "Returns the URL to redirect a user to for the Authorization step of the OAuth process."
  [& {:keys [client-id scope redirect-uri state]}]
  (str util/base-url "/oauth/authorize?" (form-encode {:scope scope
                                                       :client_id client-id
                                                       :redirect_uri redirect-uri
                                                       :state state})))

(defn exchange-auth-code
  "Exchanges the code returned from a the authorization redirect for access token and refresh token."
  [& {:keys [client-id client-secret redirect-uri code]}]
  (http/request "/oauth.exchangeAuthCode" :form {:client_id client-id
                                                 :client_secret client-secret
                                                 :redirect_uri redirect-uri
                                                 :code code}))


(defn refresh-token
  "Obtains a new access token from a refresh token."
  [& {:keys [client-id client-secret refresh-token]}]
  (http/request "/oauth.refreshToken" :form {:client_id client-id
                                             :client_secret client-secret
                                             :refresh_token refresh-token}))

(defn verify-access-token
  "Checks if an access token is still valid"
  [access-token]
  (http/request "/oauth.verifyAccessToken" :form {:access_token access-token}))