(ns full.api.contact.teams
    (:require [full.api.http :as http]))

(defn get [auth]
    (http/request "/teams.get" :auth auth :json {}))