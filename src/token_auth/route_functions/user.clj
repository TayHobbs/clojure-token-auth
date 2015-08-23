(ns token-auth.route-functions.user
  (:require [token-auth.queries.query-defs :as query]
            [buddy.hashers :as hashers]))

(defn all-users []
  (let [users (query/all-users)]
    (->> users
        (map #(update-in % [:email] str))
        (map #(update-in % [:username] str)))))

(defn create-user [email username password]
  (let [hashed-password (hashers/encrypt password)
        new-user (query/insert-user<! {:email email :username username :password hashed-password})]
  {:email (str (:email new-user)) :username (str (:username new-user)) :id (:id new-user)}))
