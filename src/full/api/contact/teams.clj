(ns full.api.contact.teams
    (:require [
        [full.core :as core]
        [full.api.http :as http]]))

(defn get [auth]
    (http/request "/teams.get" :auth auth :json {})))