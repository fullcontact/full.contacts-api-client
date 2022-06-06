(ns full.contacts-api-client.oauth
  (:require [org.httpkit.client :as http]
            [clojure.data.json :as json]
            [full.contacts-api-client.util :as util]
            [ring.util.codec :refer [form-encode]]))

(defn get-url [endpoint]
  (str util/base-url "/" util/api-version3 endpoint))

(defn request [endpoint & {:keys [json form body headers params method]
                           :or   {headers {} params {} method :post}}]
  (delay
    (let [json-req (not (nil? json))
          form-req (not (nil? form))
          res @(http/request (into
                               {:user-agent (str "Contacts API Clojure SDK v" util/version)
                                :url        (get-url endpoint)
                                :method     method
                                :headers    (into {"Content-Type" (cond json-req "application/json"
                                                                        form-req "application/x-www-form-urlencoded"
                                                                        :else "text/plain")
                                                   } headers)
                                :body       (cond
                                              json-req (json/write-str json)
                                              form-req (form-encode form)
                                              :else body)}
                               params))]
      {:status  (:status res)
       :headers (:headers res)
       :body    (if (and (re-find #"(i?)json" (or (get-in res [:headers :content-type]) "")) (not (clojure.string/blank? (:body res))))
                  (try
                    (json/read-str (:body res) :key-fn keyword)
                    (catch Exception e
                      (:body res)))
                  (:body res))})))

(defn get-authorize-url
  "Returns the URL to redirect a user to for the Authorization step of the OAuth process."
  [& {:keys [client-id scope redirect-uri state]}]
  (str util/app-url "/oauth/authorize?" (form-encode {:scope        scope
                                                      :client_id    client-id
                                                      :redirect_uri redirect-uri
                                                      :state        state})))

(defn exchange-auth-code
  "Exchanges the code returned from a the authorization redirect for access token and refresh token."
  [& {:keys [client-id client-secret redirect-uri code]}]
  (request "/oauth.exchangeAuthCode" :form {:client_id     client-id
                                            :client_secret client-secret
                                            :redirect_uri  redirect-uri
                                            :code          code}))


(defn refresh-token
  "Obtains a new access token from a refresh token."
  [& {:keys [client-id client-secret refresh-token]}]
  (request "/oauth.refreshToken" :form {:client_id     client-id
                                        :client_secret client-secret
                                        :refresh_token refresh-token}))

(defn verify-access-token
  "Checks if an access token is still valid"
  [access-token]
  (request "/oauth.verifyAccessToken" :form {:access_token access-token}))
