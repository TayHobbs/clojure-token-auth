(ns token-auth.helpers
  (:require [cheshire.core :as cheshire]
            [token-auth.queries.query-defs :as query]))

(defn parse-body [body]
  (cheshire/parse-string (slurp body) true))

(defn create-test-users []
  (query/insert-user<! {:email "test@test.com" :username "test.user" :password "asdf"})
  (query/insert-user<! {:email "test2@test.com" :username "test.user2" :password "asdf"}))
