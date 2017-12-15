(ns full.contacts-api-client.util-test
  (:use midje.sweet)
  (:require [full.contacts-api-client.util :as util]))

(facts "about util"
       (fact "baseUrl is correct"
             (= util/base-url "https://app.fullcontact.com") => true)
       (fact "get-endpoint-url is correct"
             (= (util/get-endpoint-url "/v1/test.test") "https://app.fullcontact.com/contacts-api-client/v1/test.test") => true))
