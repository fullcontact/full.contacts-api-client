(ns full.contacts-api-client.teams
  (:require [full.contacts-api-client.http :as http]))

(defn get-
  "Gets a list of teams the user belongs to."
  [auth]
  (http/request "/teams.get" :auth auth :json {}))