(ns full.api.oauth
    (:require [
        [full.core :as core]
        [full.http-kit :as http]
        [full.api.util :as util]
        [ring.util.codec :refer [form-encode]]]))


(defn get-authorize-url [& {:keys [client-id scope redirect-uri state]}]
    (str u/base-url "/oauth/authorize?" (form-encode {:scope        scope
                                                      :client_id    client-id
                                                      :redirect_uri redirect-uri
                                                      :state        state})))

(defn exchange-auth-code [& {:keys [client-id client-secret redirect-uri code]}]
    )