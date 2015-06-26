(ns token-auth.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [token-auth.core.routes :refer [auth-routes]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (route/not-found "Not Found"))

(def app
  (-> (routes auth-routes app-routes)
      (wrap-defaults (assoc-in site-defaults [:security :anti-forgery] false))))
