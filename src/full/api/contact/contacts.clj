(ns full.api.contact.contacts
    (:require [clojure.data.json :as json]
              [full.api.http :as http]))

(defn get- [auth contact-ids & {:keys [team-id]}]
    (http/request "/contacts.get" :auth auth :json {:contactIds contact-ids
                                                    :teamId team-id}))

(defn scroll [auth & {:keys [team-id scroll-cursor include-deleted size]}]
    (http/request "/contacts.scroll" :auth auth :json {:includeDeletedContacts include-deleted
                                                       :teamId                 team-id
                                                       :size                   size
                                                       :scrollCursor           scroll-cursor}))

(defn search [auth query & {:keys [team-id tag-ids search-cursor size]}]
    (http/request "/contacts.search" :auth auth :json {:tagIds       tag-ids
                                                       :teamId       team-id
                                                       :size         size
                                                       :searchQuery  query
                                                       :searchCursor search-cursor}))

(defn create- [auth contact & {:keys [team-id]}]
    (http/request "/contacts.create" :auth auth :json {:contact contact
                                                       :teamId  team-id}))

(defn update- [auth contact & {:keys [team-id]}]
    (http/request "/contacts.update" :auth auth :json {:contact contact
                                                       :teamId  team-id}))

(defn delete- [auth contact-id etag & {:keys [team-id]}]
    (http/request "/contacts.delete" :auth auth :json {:contactId  contact-id
                                                       :etag       etag
                                                       :teamId     team-id}))

(defn upload-photo [auth contact-id image & {:keys [team-id]}]
    (http/request "/contacts.uploadPhoto" :auth auth :params {:multipart [{:name "image.png"
                                                                           :content image
                                                                           :filename "image.png"}
                                                                          {:name "contact.json"
                                                                           :content (json/write-str {:contactId contact-id})
                                                                           :filename "contact.json"}]}))