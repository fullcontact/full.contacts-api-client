(ns full.api.contact.teams-test
    (:use midje.sweet)
    (:require [full.api.contact.teams :as t]
              [full.api.util :as u]
              [full.api.test-helper :as h]))

(facts "about teams"
    (fact "get- well-formed request"
        (let [access-token  (h/str-random)
              result        (h/with-mock-http
                                "{\"teams\": []"
                                #(t/get- access-token))]
            (h/verify result :params (list "/teams.get" :auth access-token :json {})) => true)))