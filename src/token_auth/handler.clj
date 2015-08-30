(ns token-auth.handler
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [token-auth.routes.user :refer :all]
            [token-auth.queries.query-defs :as query]))

(defapi app
  (swagger-ui)
  (swagger-docs
    {:info {:title "Token authentication API"}})
    user-routes)
