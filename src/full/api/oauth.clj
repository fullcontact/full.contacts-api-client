(ns full.api.oauth
    (:require [
        [full.api.http :as http]
        [full.api.util :as util]
        [ring.util.codec :refer [form-encode]]]))


(defn get-authorize-url [& {:keys [client-id scope redirect-uri state]}]
    (str u/base-url "/oauth/authorize?" (form-encode {:scope        scope
                                                      :client_id    client-id
                                                      :redirect_uri redirect-uri
                                                      :state        state})))

(defn exchange-auth-code [& {:keys [client-id client-secret redirect-uri code]}]
    (http/request "/oauth.exchangeAuthCode" :form { :client_id     client-id
                                                   :client_secret client-secret
                                                   :redirect_uri  redirect-uri
                                                   :code          code}))


(defn refresh-token [& {:keys [client-id client-secret refresh-token]}]
    (http/request "/oauth.refreshToken" :form { :client_id      client-id
                                               :client_secret  client-secret
                                               :refresh_token  refresh-token}))

(defn refresh-token [access-token]
    (http/request "/oauth.verifyAccessToken" :form {:access_token access-token }))