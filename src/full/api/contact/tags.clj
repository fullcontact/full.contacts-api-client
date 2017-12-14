(ns full.api.contact.tags
    (:require [full.api.http :as http]))

(defn get-
    "Get a list of tags by using a list of tag ids. Use :team-id to specify team instead of individual."
    [auth tag-ids & {:keys [team-id]}]
    (http/request "/tags.get" :auth auth :json {:tagIds tag-ids
                                                :teamId team-id}))

(defn scroll
    "Scrolls a list of the user's tags. Use :team-id to specify team instead of individual."
    [auth & {:keys [team-id scroll-cursor include-deleted size]}]
    (http/request "/tags.scroll" :auth auth :json {:includeDeletedTags include-deleted
                                                   :teamId             team-id
                                                   :size               size
                                                   :scrollCursor       scroll-cursor}))

(defn create-
    "Creates a new tag. Use :team-id to specify team instead of individual."
    [auth tag & {:keys [team-id]}]
    (http/request "/tags.create" :auth auth :json {:tag    tag
                                                   :teamId team-id}))

(defn update-
    "Updates and existing tag, tagId and etag are required. Use :team-id to specify team instead of individual."
    [auth tag & {:keys [team-id]}]
    (http/request "/tags.update" :auth auth :json {:tag    tag
                                                   :teamId team-id}))

(defn delete-
    "Deletes a tag. Use :team-id to specify team instead of individual."
    [auth tag-id etag & {:keys [team-id]}]
    (http/request "/tags.delete" :auth auth :json {:tagId  tag-id
                                                   :etag   etag
                                                   :teamId team-id}))