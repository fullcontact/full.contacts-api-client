(ns full.contacts-api-client.util)

(def version "0.4.0")
(def api-version "v1")
(def api-version3 "v3")

(def base-url "https://api.contactsplus.com")

(def app-url "https://app.contactsplus.com")

(defn get-endpoint-url [path]
    (str base-url "/contacts-api-client" path))

(defn get-url [endpoint]
    (str base-url "/api/" api-version endpoint))
