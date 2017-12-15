(ns full.contacts-api-client.account-test
  (:use midje.sweet)
  (:require [full.contacts-api-client.account :as a]
            [full.contacts-api-client.util :as u]
            [full.contacts-api-client.test-helper :as h]))

(facts "about account"
       (fact "get- well-formed request"
             (let [access-token (h/str-random)
                   result (h/with-mock-http
                            "{\"accountId\": \"testing\"}"
                            #(a/get- access-token))]
               (h/verify result :params (list "/account.get" :auth access-token :json {})) => true)))