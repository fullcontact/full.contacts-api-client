(ns full.api.contact.account
    (:require [full.api.http :as http]))

(defn get- [auth]
    (http/request "/account.get" :auth auth :json {}))