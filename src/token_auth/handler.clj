(ns token-auth.handler
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]))

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

  (GET* "/example" []
    :summary "An example route returning 'hello world'"
    (ok "hello world")))
