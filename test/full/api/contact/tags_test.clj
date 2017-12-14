(ns full.api.contact.tags-test
    (:use midje.sweet)
    (:require [full.api.contact.tags :as t]
              [full.api.util :as u]
              [full.api.test-helper :as h]))

(facts "about tags"
    (fact "get- well-formed request"
        (let [access-token  (h/str-random)
              tag-ids       [(h/str-random) (h/str-random)]
              result        (h/with-mock-http
                                "{\"tags\": []}"
                                #(t/get- access-token tag-ids))]
            (h/verify result :params (list "/tags.get" :auth access-token :json {:tagIds tag-ids :teamId nil})) => true))
    (fact "get- well-formed request with team"
        (let [access-token  (h/str-random)
              tag-ids       [(h/str-random) (h/str-random)]
              team-id       (h/str-random)
              result        (h/with-mock-http
                                "{\"tags\": []}"
                                #(t/get- access-token tag-ids :team-id team-id))]
            (h/verify result :params (list "/tags.get" :auth access-token :json {:tagIds tag-ids :teamId team-id})) => true))
    (fact "scroll well-formed request"
        (let [access-token  (h/str-random)
              result        (h/with-mock-http
                                "{\"tags\": []}"
                                #(t/scroll access-token))]
            (h/verify result :params (list "/tags.scroll" :auth access-token :json {:includeDeletedTags nil :teamId nil :size nil :scrollCursor nil})) => true))
    (fact "scroll well-formed request with team"
        (let [access-token  (h/str-random)
              team-id       (h/str-random)
              result        (h/with-mock-http
                                "{\"tags\": []}"
                                #(t/scroll access-token :team-id team-id))]
            (h/verify result :params (list "/tags.scroll" :auth access-token :json {:includeDeletedTags nil :teamId team-id :size nil :scrollCursor nil})) => true))
    (fact "scroll well-formed request with all options"
        (let [access-token  (h/str-random)
              team-id       (h/str-random)
              cursor        (h/str-random)
              size          10
              result        (h/with-mock-http
                                "{\"tags\": []}"
                                #(t/scroll access-token :team-id team-id :include-deleted true :size size :scroll-cursor cursor))]
            (h/verify result :params (list "/tags.scroll" :auth access-token :json {:includeDeletedTags true :teamId team-id :size size :scrollCursor cursor})) => true))
    (fact "create- well-formed request"
        (let [access-token  (h/str-random)
              tag           {:tagData {:name (h/str-random)}}
              result        (h/with-mock-http
                                "{\"tag\": {}}"
                                #(t/create- access-token tag))]
            (h/verify result :params (list "/tags.create" :auth access-token :json {:tag tag :teamId nil})) => true))
    (fact "create- well-formed request with team"
        (let [access-token  (h/str-random)
              tag           {:tagData {:name (h/str-random)}}
              team-id       (h/str-random)
              result        (h/with-mock-http
                                "{\"tag\": {}}"
                                #(t/create- access-token tag :team-id team-id))]
            (h/verify result :params (list "/tags.create" :auth access-token :json {:tag tag :teamId team-id})) => true))
    (fact "update- well-formed request"
        (let [access-token  (h/str-random)
              tag           {:tagId (h/str-random) :tagData {:name (h/str-random)}}
              result        (h/with-mock-http
                                "{\"tag\": {}}"
                                #(t/update- access-token tag))]
            (h/verify result :params (list "/tags.update" :auth access-token :json {:tag tag :teamId nil})) => true))
    (fact "create- well-formed request with team"
        (let [access-token  (h/str-random)
              tag           {:tagId (h/str-random) :tagData {:name (h/str-random)}}
              team-id       (h/str-random)
              result        (h/with-mock-http
                                "{\"tag\": {}}"
                                #(t/update- access-token tag :team-id team-id))]
            (h/verify result :params (list "/tags.update" :auth access-token :json {:tag tag :teamId team-id})) => true))
    (fact "delete- well-formed request"
        (let [access-token  (h/str-random)
              tag-id        (h/str-random)
              etag          (h/str-random)
              result        (h/with-mock-http
                                "{}"
                                #(t/delete- access-token tag-id etag))]
            (h/verify result :params (list "/tags.delete" :auth access-token :json {:tagId tag-id :etag etag :teamId nil})) => true))
    (fact "delete- well-formed request with team"
        (let [access-token  (h/str-random)
              tag-id        (h/str-random)
              etag          (h/str-random)
              team-id       (h/str-random)
              result        (h/with-mock-http
                                "{}"
                                #(t/delete- access-token tag-id etag :team-id team-id))]
            (h/verify result :params (list "/tags.delete" :auth access-token :json {:tagId tag-id :etag etag :teamId team-id})) => true)))