(ns full.contacts-api-client.util)

(def base-url "https://app.fullcontact.com")

(defn get-endpoint-url [path]
    (str base-url "/contacts-api-client" path))