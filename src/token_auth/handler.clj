(ns token-auth.handler
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [token-auth.route-functions.all-users :refer :all]
            [token-auth.queries.query-defs :as query]))

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
