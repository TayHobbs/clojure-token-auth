(ns token-auth.route-functions.user
  (:require [token-auth.queries.query-defs :as query]
            [buddy.hashers :as hashers]
            [ring.util.http-response :as response]))

(defn all-users []
  (let [users (query/all-users)]
    (->> users
        (map #(update-in % [:email] str))
        (map #(update-in % [:username] str)))))

(defn create-user [username email password]
    (let [hashed-password (hashers/encrypt password)
          new-user (query/insert-user<! {:email email :username username :password hashed-password})]
    (response/created {:email (str (:email new-user)) :username (str (:username new-user)) :id (:id new-user)})))

(defn create-valid-user [username email password]
  (let [username-query   (query/get-user-by-username {:username  username})
        email-query      (query/get-user-by-email {:email email})
        email-exists?    (not-empty email-query)
        username-exists? (not-empty username-query)]
    (cond
      (and username-exists? email-exists?) (response/conflict {:error "Username and Email already exists"})
      email-exists?                        (response/conflict {:error "Email already exists"})
      username-exists?                     (response/conflict {:error "Username already exists"})
      :else                                (create-user username email password))))
