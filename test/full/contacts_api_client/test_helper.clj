(ns full.contacts-api-client.test-helper
  (:use mockery.core))

(defn with-mock-http [r f]
  (with-mock mock
             {:target :full.contacts-api-client.http/request
              :return (delay r)}
             (let [res @(f)]
               (assoc @mock :res res))))

(defn str-random [] (.toString (java.util.UUID/randomUUID)))

(defn verify [mock & {:keys [call-count params res]
                      :or   {call-count 1 params '() res nil}}]
  (and (true? (:called? mock))
       (= call-count (:call-count mock))
       (or (nil? res) (= res (:res mock)))
       (= (:call-args mock) params)))