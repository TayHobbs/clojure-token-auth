(ns token-auth.core.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]))

(defroutes auth-routes
  (GET "/" [] "Hello World"))
