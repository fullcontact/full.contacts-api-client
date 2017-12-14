(ns full.api.contact.contacts
    (:require [clojure.data.json :as json]
              [full.api.http :as http]))

(defn get-
    "Get a list of contacts by using a list of contact ids. Use :team-id to specify team instead of individual."
    [auth contact-ids & {:keys [team-id]}]
    (http/request "/contacts.get" :auth auth :json {:contactIds contact-ids
                                                    :teamId team-id}))

(defn scroll
    "Scrolls the user's list of contacts. Use :team-id to specify team instead of individual."
    [auth & {:keys [team-id scroll-cursor include-deleted size]}]
    (http/request "/contacts.scroll" :auth auth :json {:includeDeletedContacts include-deleted
                                                       :teamId                 team-id
                                                       :size                   size
                                                       :scrollCursor           scroll-cursor}))

(defn search
    "Searches the user's list of contacts. Use :team-id to specify team instead of individual."
    [auth query & {:keys [team-id tag-ids search-cursor size]}]
    (http/request "/contacts.search" :auth auth :json {:tagIds       tag-ids
                                                       :teamId       team-id
                                                       :size         size
                                                       :searchQuery  query
                                                       :searchCursor search-cursor}))

(defn create-
    "Creates a new contact. Use :team-id to specify team instead of individual."
    [auth contact & {:keys [team-id]}]
    (http/request "/contacts.create" :auth auth :json {:contact contact
                                                       :teamId  team-id}))

(defn update-
    "Updates a contact, contactId and etag are required. Use :team-id to specify team instead of individual."
    [auth contact & {:keys [team-id]}]
    (http/request "/contacts.update" :auth auth :json {:contact contact
                                                       :teamId  team-id}))

(defn delete-
    "Deletes a contact. Use :team-id to specify team instead of individual."
    [auth contact-id etag & {:keys [team-id]}]
    (http/request "/contacts.delete" :auth auth :json {:contactId  contact-id
                                                       :etag       etag
                                                       :teamId     team-id}))

(defn manage-tags
    "Add or remove tags from contacts."
    [auth contact-ids & {:keys [add-tag-ids remove-tag-ids team-id]}]
    (http/request "/contacts.manageTags" :auth auth :json {:contactIds   contact-ids
                                                           :addTagIds    add-tag-ids
                                                           :removeTagIds remove-tag-ids
                                                           :teamId       team-id}))

(defn upload-photo
    "Uploads and prepend a photo to a contact. Use :team-id to specify team instead of individual."
    [auth contact-id image & {:keys [team-id]}]
    (http/request "/contacts.uploadPhoto" :auth auth :params {:multipart [{:name "image.png"
                                                                           :content image
                                                                           :filename "image.png"}
                                                                          {:name "contact.json"
                                                                           :content (json/write-str {:contactId contact-id})
                                                                           :filename "contact.json"}]}))