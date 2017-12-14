(ns full.api.contact.webhooks
    (:require [full.api.http :as http]))

(defn get- [auth webhook-ids & {:keys [team-id page]}]
    (http/request "/webhooks.get" :auth auth :json {:webhookIds webhook-ids
                                                   :teamId     team-id
                                                   :page       page}))

(defn search [auth & {:keys [url page trigger-ids team-id]}]
    (http/request "/webhooks.search" :auth auth :json {:url        url
                                                      :teamId     team-id
                                                      :triggerIds trigger-ids
                                                      :page       page}))

(defn create- [auth url trigger-ids & {:keys [team-id]}]
    (http/request "/webhooks.create" :auth auth :json {:url        url
                                                      :teamId     team-id
                                                      :triggerIds trigger-ids}))

(defn delete- [auth webhook-id & {:keys [team-id]}]
    (http/request "/webhooks.delete" :auth auth :json {:webhookId webhook-id
                                                      :teamId    team-id}))

(defn get-batches [auth batch-id webhook-id & {:keys [team-id]}]
    (http/request "/webhooks.getBatches" :auth auth :json {:webhookId webhook-id
                                                          :batchId   batch-id
                                                          :teamId    team-id}))

(defn get-triggers [auth]
    (http/request "/webhooks.getTriggers" :auth auth :json {}))