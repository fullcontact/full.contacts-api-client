(ns full.api.contact.account-test
    (:use midje.sweet)
    (:require [full.api.contact.account :as a]
              [full.api.util :as u]
              [full.api.test-helper :as h]))

(facts "about account"
    (fact "get- well-formed request"
        (let [access-token  (h/str-random)
              result        (h/with-mock-http
                                "{\"accountId\": \"testing\"}"
                                #(a/get- access-token))]
            (h/verify result :params (list "/account.get" :auth access-token :json {})) => true)))