(defproject fullcontact/contacts-api-client "0.4.0-SNAPSHOT"
    :description "Clojure SDK for Contacts+ API"
    :url "https://github.com/contactsplusapp/contacts-api-client"
    :license {:name "Eclipse Public License - v 1.0"
              :url "http://www.eclipse.org/legal/epl-v10.html"
              :distribution :repo}
    :dependencies [[org.clojure/clojure "1.11.1"]
                   [com.fasterxml.jackson.core/jackson-core "2.13.2"]
                   [com.fasterxml.jackson.core/jackson-databind "2.13.2.2"]
                   [http-kit/http-kit "2.5.3"]
                   [ring/ring-codec "1.2.0"]
                   [fullcontact/full.core "1.1.1"]
                   [clj-time "0.15.2"]
                   [fullcontact/full.async "1.1.0"]]
    :profiles {:dev {:dependencies [[midje "1.10.5"] [mockery "0.1.2"]]}}
    :plugins [[lein-environ "1.1.0"]
              [lein-midje "3.2.2"]]
    :deploy-repositories [["releases" {:url "https://clojars.org/repo/" :creds :gpg}]]
    :min-lein-version "2.5.0"
    :clean-targets ^{:protect false} ["target"]
    :aot :all)
