(ns full.api.contact.contacts-test
    (:use midje.sweet)
    (:require [full.api.contact.contacts :as c]
              [full.api.util :as u]
              [full.api.test-helper :as h]))

(facts "about contacts"
    (fact "get- well-formed request"
        (let [access-token  (h/str-random)
              contact-ids   [(h/str-random) (h/str-random)]
              result        (h/with-mock-http
                                "{\"contacts\": []}"
                                #(c/get- access-token contact-ids))]
            (h/verify result :params (list "/contacts.get" :auth access-token :json {:contactIds contact-ids :teamId nil})) => true))
    (fact "get- well-formed request with team"
        (let [access-token  (h/str-random)
              contact-ids   [(h/str-random) (h/str-random)]
              team-id       (h/str-random)
              result        (h/with-mock-http
                                "{\"contacts\": []}"
                                #(c/get- access-token contact-ids :team-id team-id))]
            (h/verify result :params (list "/contacts.get" :auth access-token :json {:contactIds contact-ids :teamId team-id})) => true))
    (fact "scroll well-formed request"
        (let [access-token  (h/str-random)
              result        (h/with-mock-http
                                "{\"contacts\": []}"
                                #(c/scroll access-token))]
            (h/verify result :params (list "/contacts.scroll" :auth access-token :json {:includeDeletedContacts nil :teamId nil :size nil :scrollCursor nil})) => true))
    (fact "scroll well-formed request with team"
        (let [access-token  (h/str-random)
              team-id       (h/str-random)
              result        (h/with-mock-http
                                "{\"contacts\": []}"
                                #(c/scroll access-token :team-id team-id))]
            (h/verify result :params (list "/contacts.scroll" :auth access-token :json {:includeDeletedContacts nil :teamId team-id :size nil :scrollCursor nil})) => true))
    (fact "scroll well-formed request with all options"
        (let [access-token  (h/str-random)
              team-id       (h/str-random)
              cursor        (h/str-random)
              size          10
              result        (h/with-mock-http
                                "{\"contacts\": []}"
                                #(c/scroll access-token :team-id team-id :include-deleted true :size size :scroll-cursor cursor))]
            (h/verify result :params (list "/contacts.scroll" :auth access-token :json {:includeDeletedContacts true :teamId team-id :size size :scrollCursor cursor})) => true))
    (fact "search well-formed request"
        (let [access-token  (h/str-random)
              query         (h/str-random)
              result        (h/with-mock-http
                                "{\"contacts\": []}"
                                #(c/search access-token query))]
            (h/verify result :params (list "/contacts.search" :auth access-token :json {:tagIds nil :teamId nil :size nil :searchQuery query :searchCursor nil})) => true))
    (fact "search well-formed request with team"
        (let [access-token  (h/str-random)
              query         (h/str-random)
              team-id       (h/str-random)
              result        (h/with-mock-http
                                "{\"contacts\": []}"
                                #(c/search access-token query :team-id team-id))]
            (h/verify result :params (list "/contacts.search" :auth access-token :json {:tagIds nil :teamId team-id :size nil :searchQuery query :searchCursor nil})) => true))
    (fact "search well-formed request with all options"
        (let [access-token  (h/str-random)
              query         (h/str-random)
              team-id       (h/str-random)
              cursor        (h/str-random)
              size          10
              tag-ids       [(h/str-random)]
              result        (h/with-mock-http
                                "{\"contacts\": []}"
                                #(c/search access-token query :team-id team-id :tag-ids tag-ids :size size :search-cursor cursor))]
            (h/verify result :params (list "/contacts.search" :auth access-token :json {:tagIds tag-ids :teamId team-id :size size :searchQuery query :searchCursor cursor})) => true))
    (fact "create- well-formed request"
        (let [access-token  (h/str-random)
              contact       {:contactData {:name {:familyName (h/str-random)}}}
              result        (h/with-mock-http
                                "{\"contact\": {}}"
                                #(c/create- access-token contact))]
            (h/verify result :params (list "/contacts.create" :auth access-token :json {:contact contact :teamId nil})) => true))
    (fact "create- well-formed request with team"
        (let [access-token  (h/str-random)
              contact       {:contactData {:name {:familyName (h/str-random)}}}
              team-id       (h/str-random)
              result        (h/with-mock-http
                                "{\"contact\": {}}"
                                #(c/create- access-token contact :team-id team-id))]
            (h/verify result :params (list "/contacts.create" :auth access-token :json {:contact contact :teamId team-id})) => true))
    (fact "update- well-formed request"
        (let [access-token  (h/str-random)
              contact       {:contactId (h/str-random) :contactData {:name {:familyName (h/str-random)}}}
              result        (h/with-mock-http
                                "{\"contact\": {}}"
                                #(c/update- access-token contact))]
            (h/verify result :params (list "/contacts.update" :auth access-token :json {:contact contact :teamId nil})) => true))
    (fact "create- well-formed request with team"
        (let [access-token  (h/str-random)
              contact       {:contactId (h/str-random) :contactData {:name {:familyName (h/str-random)}}}
              team-id       (h/str-random)
              result        (h/with-mock-http
                                "{\"contact\": {}}"
                                #(c/update- access-token contact :team-id team-id))]
            (h/verify result :params (list "/contacts.update" :auth access-token :json {:contact contact :teamId team-id})) => true))
    (fact "delete- well-formed request"
        (let [access-token  (h/str-random)
              contact-id    (h/str-random)
              etag          (h/str-random)
              result        (h/with-mock-http
                                "{}"
                                #(c/delete- access-token contact-id etag))]
            (h/verify result :params (list "/contacts.delete" :auth access-token :json {:contactId contact-id :etag etag :teamId nil})) => true))
    (fact "delete- well-formed request with team"
        (let [access-token  (h/str-random)
              contact-id    (h/str-random)
              etag          (h/str-random)
              team-id       (h/str-random)
              result        (h/with-mock-http
                                "{}"
                                #(c/delete- access-token contact-id etag :team-id team-id))]
            (h/verify result :params (list "/contacts.delete" :auth access-token :json {:contactId contact-id :etag etag :teamId team-id})) => true))
    (fact "manage-tags well-formed request add only"
        (let [access-token  (h/str-random)
              contact-ids   [(h/str-random)]
              add-tag-ids   [(h/str-random) (h/str-random)]
              result        (h/with-mock-http
                                "{}"
                                #(c/manage-tags access-token contact-ids :add-tag-ids add-tag-ids))]
            (h/verify result :params (list "/contacts.manageTags" :auth access-token :json {:contactIds contact-ids :addTagIds add-tag-ids :removeTagIds nil :teamId nil})) => true))
    (fact "manage-tags well-formed request"
        (let [access-token   (h/str-random)
              contact-ids    [(h/str-random)]
              add-tag-ids    [(h/str-random) (h/str-random)]
              remove-tag-ids [(h/str-random)]
              result          (h/with-mock-http
                                "{}"
                                #(c/manage-tags access-token contact-ids :add-tag-ids add-tag-ids :remove-tag-ids remove-tag-ids))]
            (h/verify result :params (list "/contacts.manageTags" :auth access-token :json {:contactIds contact-ids :addTagIds add-tag-ids :removeTagIds remove-tag-ids :teamId nil})) => true))
    (fact "manage-tags well-formed request with team"
        (let [access-token   (h/str-random)
              contact-ids    [(h/str-random)]
              add-tag-ids    [(h/str-random) (h/str-random)]
              remove-tag-ids [(h/str-random)]
              team-id        (h/str-random)
              result         (h/with-mock-http
                                "{}"
                                #(c/manage-tags access-token contact-ids :add-tag-ids add-tag-ids :remove-tag-ids remove-tag-ids :team-id team-id))]
            (h/verify result :params (list "/contacts.manageTags" :auth access-token :json {:contactIds contact-ids :addTagIds add-tag-ids :removeTagIds remove-tag-ids :teamId team-id})) => true))
    (fact "upload-photo well-formed request with team"
        (let [access-token  (h/str-random)
              contact-id    (h/str-random)
              content       (h/str-random)
              team-id       (h/str-random)
              result        (h/with-mock-http
                                "{}"
                                #(c/upload-photo access-token contact-id content :team-id team-id))]
            (h/verify result :params (list "/contacts.uploadPhoto" :auth access-token :params {:multipart [{:name "image.png" :content content :filename "image.png"} {:name "contact.json" :content (str "{\"contactId\":" "\"" contact-id "\"}") :filename "contact.json"}]})) => true)))