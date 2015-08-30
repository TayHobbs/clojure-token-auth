(ns token-auth.routes.user
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [token-auth.route-functions.user :refer :all]
            [token-auth.queries.query-defs :as query]))

(defroutes* user-routes
  (context "/api" []
    (GET* "/all-users" []
      :tags    ["Users"]
      :summary "Returns all users"
      (ok (all-users)))


    (POST* "/user"    {:as request}
         :tags        ["Users"]
         :return      {:email String :username String :id Long}
         :body-params [email :- String username :- String password :- String]
         :summary     "Create a new user"
         (create-valid-user username email password))))
