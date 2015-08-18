(ns token-auth.handler
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [token-auth.queries.query-defs :as query]))

(defn all-users []
  (let [users (query/all-users)]
    (->> users
        (map #(update-in % [:email] str))
        (map #(update-in % [:username] str)))))

(s/defschema Thingie
  {:id Long
   :hot Boolean
   :tag (s/enum :kikka :kukka)
   :chief [{:name String
            :type #{{:id String}}}]})

(defapi app
  (swagger-ui)
  (swagger-docs
    {:info {:title "Sample api"}})

  (GET* "/all-users" []
    :summary "An example route returning 'hello world'"
    (ok (all-users))))
