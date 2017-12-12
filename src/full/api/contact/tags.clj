(ns full.api.contact.tags
    (:require [full.api.http :as http]))

(defn get [auth tag-ids & {:keys [team-id]}]
    (http/request "/tags.get" :auth auth :json {:tagIds tag-ids
                                                :teamId team-id}))

(defn scroll [auth & {:keys [team-id scroll-cursor include-deleted size]}]
    (http/request "/tags.scroll" :auth auth :json {:includeDeletedTags include-deleted
                                                   :teamId             team-id
                                                   :size               size
                                                   :scrollCursor       scroll-cursor}))

(defn create [auth name & {:keys [team-id]}]
    (http/request "/tags.create" :auth auth :json {:tag    {:tagData {:name name}}
                                                   :teamId team-id}))

(defn update [auth tag-id name & {:keys [team-id]}]
    (http/request "/tags.update" :auth auth :json {:tag    {:tagId tag-id
                                                            :tagData {:name name}}
                                                   :teamId team-id}))

(defn delete [auth tag-id etag & {:keys [team-id]}]
    (http/request "/tags.delete" :auth auth :json {:tagId  tag-id
                                                   :etag   etag
                                                   :teamId team-id}))