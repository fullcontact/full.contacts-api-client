(ns full.api.request
    (:require [
        [full.core :as core]
        [org.httpkit.client :as http]
        [clojure.data.json :as json]
        [full.api.util :as util]]))

(defn request [params & {:keys [json form body]}]
    (delay
        (let [res @(http/request
                        (into
                            {:user-agent "full.api.contacts"
                            :body (json/encode json)}
                            params))]
            {:status  (:status res)
            :headers  (:headers res)
            :body     (json/decode (:body res))})))
