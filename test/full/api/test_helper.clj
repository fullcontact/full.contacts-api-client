(ns full.api.test-helper
    (:use mockery.core))

(defn with-mock-http [r f]
    (with-mock mock
        {:target :full.api.http/request
         :return (delay r)}
         {:result (f) :mock @mock }))

(defn str-random [] (.toString (java.util.UUID/randomUUID)))

(defn verify-mock [mock params & {:keys [call-count params]
                                  :or   {call-count 1 params '()}}]
    (and
        (true? (:called? mock))
        (= call-count (:call-count mock))
        (every? #(> (.indexOf (:call-args mock) %) -1) params)))