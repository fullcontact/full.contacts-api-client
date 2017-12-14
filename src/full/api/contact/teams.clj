(ns full.api.contact.teams
    (:require [full.api.http :as http]))

(defn get-
    "Gets a list of teams the user belongs to."
    [auth]
    (http/request "/teams.get" :auth auth :json {}))