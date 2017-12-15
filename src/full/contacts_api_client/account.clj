(ns full.contacts-api-client.account
  (:require [full.contacts-api-client.http :as http]))

(defn get-
  "Get the account details and profile for a user"
  [auth]
  (http/request "/account.get" :auth auth :json {}))