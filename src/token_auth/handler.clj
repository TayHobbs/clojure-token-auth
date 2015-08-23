(ns token-auth.handler
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [token-auth.route-functions.user :refer :all]
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
    {:info {:title "Token authentication API"}})

  (context* "/api" []

  (GET* "/all-users" []
    :tags    ["All users"]
    :summary "Returns all users"
    (ok (all-users)))

  (POST* "/user"    {:as request}
       :tags        ["Create user"]
       :return      {:email String :username String :id Long}
       :body-params [email :- String username :- String password :- String]
       :summary     "Create a new user"
       (created (create-user email username password)))))
