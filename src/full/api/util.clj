(ns full.api.util)

(def base-url "https://app.fullcontact.com")

(defn get-endpoint-url [path]
    (str base-url "/api" path))