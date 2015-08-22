(ns token-auth.route-functions.user
  (:require [token-auth.queries.query-defs :as query]))

(defn all-users []
  (let [users (query/all-users)]
    (->> users
        (map #(update-in % [:email] str))
        (map #(update-in % [:username] str)))))
