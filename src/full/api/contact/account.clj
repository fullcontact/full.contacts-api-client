(ns full.api.contact.account
    (:require [
        [full.core :as core]
        [full.api.http :as http]]))

(defn get [auth]
    (http/request "/account.get" :auth auth :json {})))