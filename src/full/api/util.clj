(ns full.api.util
    (:require [
        [full.core :as core]
        [full.http-kit :as http]]))

(def base-url "https://app.fullcontact.com")

(defn get-endpoint-url [path]
    (str base-url "/api" path))