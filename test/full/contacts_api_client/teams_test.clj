(ns full.contacts-api-client.teams-test
  (:use midje.sweet)
  (:require [full.contacts-api-client.teams :as t]
            [full.contacts-api-client.util :as u]
            [full.contacts-api-client.test-helper :as h]))

(facts "about teams"
       (fact "get- well-formed request"
             (let [access-token (h/str-random)
                   result (h/with-mock-http
                            "{\"teams\": []}"
                            #(t/get- access-token))]
               (h/verify result :params (list "/teams.get" :auth access-token :json {})) => true)))