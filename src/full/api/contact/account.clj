(ns full.api.contact.account
    (:require [full.api.http :as http]))

(defn get-
    "Get the account details and profile for a user"
    [auth]
    (http/request "/account.get" :auth auth :json {}))