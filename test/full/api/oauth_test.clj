(ns full.api.oauth-test
    (:use midje.sweet)
    (:require [full.api.oauth :as o]
              [full.api.util :as u]
              [ring.util.codec :refer [form-encode]]
              [full.api.test-helper :as h]))

(facts "about oauth"
   (fact "get authorization url is correct"
    (let [client-id     (h/str-random)
          state         (h/str-random)
          redirect-uri  (h/str-random)
          scope         (h/str-random)
          expected      (str u/base-url "/oauth/authorize?" (form-encode {:scope scope :client_id client-id :redirect_uri redirect-uri :state state}))
          actual        (o/get-authorize-url :client-id client-id :scope scope :redirect-uri redirect-uri :state state)]
          (= expected actual) => true))
    (fact "exchange-auth-code well-formed request"
        (let [client-id     (h/str-random)
              client-secret (h/str-random)
              code          (h/str-random)
              redirect-uri  (h/str-random)
              result        (h/with-mock-http
                                "{\"refresh_token\": \"test-token\""
                                #(o/exchange-auth-code :client-id client-id :client-secret client-secret :code code :redirect-uri redirect-uri))]
            (h/verify result :params (list "/oauth.exchangeAuthCode" :form {:client_id client-id :client_secret client-secret :redirect_uri redirect-uri :code code})) => true))
    (fact "refresh-token well-formed request"
        (let [client-id     (h/str-random)
              client-secret (h/str-random)
              refresh-token (h/str-random)
              result        (h/with-mock-http
                                "{\"access_token\": \"test-token\""
                                #(o/refresh-token :client-id client-id :client-secret client-secret :refresh-token refresh-token))]
            (h/verify result :params (list "/oauth.refreshToken" :form {:client_id client-id :client_secret client-secret :refresh_token refresh-token})) => true))
    (fact "verify-access-token well-formed request"
        (let [access-token  (h/str-random)
              result        (h/with-mock-http
                                "{}"
                                #(o/verify-access-token access-token))]
            (h/verify result :params (list "/oauth.verifyAccessToken" :form {:access_token access-token})) => true)))