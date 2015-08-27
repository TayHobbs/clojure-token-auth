(ns token-auth.handler
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [token-auth.route-functions.user :refer :all]
            [token-auth.queries.query-defs :as query]))

(defapi app
  (swagger-ui)
  (swagger-docs
    {:info {:title "Token authentication API"}})

  (context* "/api" []

  (GET* "/all-users" []
    :tags    ["Users"]
    :summary "Returns all users"
    (ok (all-users)))

  (POST* "/user"    {:as request}
       :tags        ["Users"]
       :return      {:email String :username String :id Long}
       :body-params [email :- String username :- String password :- String]
       :summary     "Create a new user"
       (created (create-user email username password)))))
