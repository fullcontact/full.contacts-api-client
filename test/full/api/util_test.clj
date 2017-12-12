(ns full.api.util-test
    (:use midje.sweet)
    (:require [full.api.util :as util]))

(facts "about util"
    (fact "baseUrl is correct"
        (= util/base-url "https://app.fullcontact.com") => true)
    (fact "get-endpoint-url is correct"
        (= (util/get-endpoint-url "/v1/test.test") "https://app.fullcontact.com/api/v1/test.test") => true))
