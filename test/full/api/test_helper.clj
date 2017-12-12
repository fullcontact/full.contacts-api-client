(ns full.api.test-helper
    (:use mockery.core))

(defn mock-http [r f]
    (with-mock mock
        {:target :full.api.http/request
         :return (delay r)}
         (f)
         @mock))