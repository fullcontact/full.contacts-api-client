(ns full.contacts-api-client.webhooks
  (:require [full.contacts-api-client.http :as http]))

(defn get-
  "Get a list of webhooks by using a list of webhook ids. Use :team-id to specify team instead of individual."
  [auth webhook-ids & {:keys [team-id page]}]
  (http/request "/webhooks.get" :auth auth :json {:webhookIds webhook-ids
                                                  :teamId team-id
                                                  :page page}))

(defn search
  "Searches a user's list of webhooks. Use :team-id to specify team instead of individual."
  [auth & {:keys [url page trigger-ids team-id]}]
  (http/request "/webhooks.search" :auth auth :json {:url url
                                                     :teamId team-id
                                                     :triggerIds trigger-ids
                                                     :page page}))

(defn create-
  "Creates a new webhook. Use :team-id to specify team instead of individual."
  [auth url trigger-ids & {:keys [team-id]}]
  (http/request "/webhooks.create" :auth auth :json {:url url
                                                     :teamId team-id
                                                     :triggerIds trigger-ids}))

(defn delete-
  "Deletes an existing webhook. Use :team-id to specify team instead of individual."
  [auth webhook-id & {:keys [team-id]}]
  (http/request "/webhooks.delete" :auth auth :json {:webhookId webhook-id
                                                     :teamId team-id}))

(defn get-batches
  "Gets a batch of webhooks. Use :team-id to specify team instead of individual."
  [auth batch-id webhook-id & {:keys [team-id]}]
  (http/request "/webhooks.getBatches" :auth auth :json {:webhookId webhook-id
                                                         :batchId batch-id
                                                         :teamId team-id}))

(defn get-triggers
  "Get a list of possible triggerIds that can be used to create a new webhook"
  [auth]
  (http/request "/webhooks.getTriggers" :auth auth :json {}))