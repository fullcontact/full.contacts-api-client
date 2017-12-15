(ns full.contacts-api-client.webhooks-test
  (:use midje.sweet)
  (:require [full.contacts-api-client.webhooks :as w]
            [full.contacts-api-client.util :as u]
            [full.contacts-api-client.test-helper :as h]))

(facts "about webhooks"
       (fact "get- well-formed request"
             (let [access-token (h/str-random)
                   webhook-ids [(h/str-random) (h/str-random)]
                   result (h/with-mock-http
                            "{\"webhooks\": []}"
                            #(w/get- access-token webhook-ids))]
               (h/verify result :params (list "/webhooks.get" :auth access-token :json {:webhookIds webhook-ids :teamId nil :page nil})) => true))
       (fact "get- well-formed request with team"
             (let [access-token (h/str-random)
                   webhook-ids [(h/str-random) (h/str-random)]
                   team-id (h/str-random)
                   result (h/with-mock-http
                            "{\"webhooks\": []}"
                            #(w/get- access-token webhook-ids :team-id team-id))]
               (h/verify result :params (list "/webhooks.get" :auth access-token :json {:webhookIds webhook-ids :teamId team-id :page nil})) => true))
       (fact "get- well-formed request with team and page"
             (let [access-token (h/str-random)
                   webhook-ids [(h/str-random) (h/str-random)]
                   page 10
                   team-id (h/str-random)
                   result (h/with-mock-http
                            "{\"webhooks\": []}"
                            #(w/get- access-token webhook-ids :team-id team-id :page page))]
               (h/verify result :params (list "/webhooks.get" :auth access-token :json {:webhookIds webhook-ids :teamId team-id :page page})) => true))
       (fact "search well-formed request"
             (let [access-token (h/str-random)
                   url (h/str-random)
                   result (h/with-mock-http
                            "{\"webhooks\": []}"
                            #(w/search access-token :url url))]

               (h/verify result :params (list "/webhooks.search" :auth access-token :json {:url url :teamId nil :triggerIds nil :page nil})) => true))
       (fact "search well-formed request with team"
             (let [access-token (h/str-random)
                   url (h/str-random)
                   team-id (h/str-random)
                   result (h/with-mock-http
                            "{\"webhooks\": []}"
                            #(w/search access-token :url url :team-id team-id))]
               (h/verify result :params (list "/webhooks.search" :auth access-token :json {:url url :teamId team-id :triggerIds nil :page nil})) => true))
       (fact "search well-formed request with all options"
             (let [access-token (h/str-random)
                   url (h/str-random)
                   trigger-ids [(h/str-random)]
                   page 20
                   team-id (h/str-random)
                   result (h/with-mock-http
                            "{\"webhooks\": []}"
                            #(w/search access-token :url url :team-id team-id :page page :trigger-ids trigger-ids))]
               (h/verify result :params (list "/webhooks.search" :auth access-token :json {:url url :teamId team-id :triggerIds trigger-ids :page page})) => true))
       (fact "create- well-formed request"
             (let [access-token (h/str-random)
                   url (h/str-random)
                   trigger-ids [(h/str-random)]
                   result (h/with-mock-http
                            "{\"webhook\": {}}"
                            #(w/create- access-token url trigger-ids))]
               (h/verify result :params (list "/webhooks.create" :auth access-token :json {:url url :teamId nil :triggerIds trigger-ids})) => true))
       (fact "create- well-formed request with team"
             (let [access-token (h/str-random)
                   url (h/str-random)
                   trigger-ids [(h/str-random)]
                   team-id (h/str-random)
                   result (h/with-mock-http
                            "{\"webhook\": {}}"
                            #(w/create- access-token url trigger-ids :team-id team-id))]
               (h/verify result :params (list "/webhooks.create" :auth access-token :json {:url url :teamId team-id :triggerIds trigger-ids})) => true))
       (fact "delete- well-formed request"
             (let [access-token (h/str-random)
                   webhook-id (h/str-random)
                   result (h/with-mock-http
                            "{}"
                            #(w/delete- access-token webhook-id))]
               (h/verify result :params (list "/webhooks.delete" :auth access-token :json {:webhookId webhook-id :teamId nil})) => true))
       (fact "delete- well-formed request with team"
             (let [access-token (h/str-random)
                   webhook-id (h/str-random)
                   team-id (h/str-random)
                   result (h/with-mock-http
                            "{}"
                            #(w/delete- access-token webhook-id :team-id team-id))]
               (h/verify result :params (list "/webhooks.delete" :auth access-token :json {:webhookId webhook-id :teamId team-id})) => true))
       (fact "get-batches well-formed request"
             (let [access-token (h/str-random)
                   webhook-id (h/str-random)
                   batch-id (h/str-random)
                   result (h/with-mock-http
                            "{}"
                            #(w/get-batches access-token batch-id webhook-id))]
               (h/verify result :params (list "/webhooks.getBatches" :auth access-token :json {:webhookId webhook-id :batchId batch-id :teamId nil})) => true))
       (fact "get-batches well-formed request with team"
             (let [access-token (h/str-random)
                   webhook-id (h/str-random)
                   batch-id (h/str-random)
                   team-id (h/str-random)
                   result (h/with-mock-http
                            "{}"
                            #(w/get-batches access-token batch-id webhook-id :team-id team-id))]
               (h/verify result :params (list "/webhooks.getBatches" :auth access-token :json {:webhookId webhook-id :batchId batch-id :teamId team-id})) => true))
       (fact "get-triggers well-formed request"
             (let [access-token (h/str-random)
                   result (h/with-mock-http
                            "{\"triggers\": []}"
                            #(w/get-triggers access-token))]
               (h/verify result :params (list "/webhooks.getTriggers" :auth access-token :json {})) => true)))