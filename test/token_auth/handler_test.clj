(ns token-auth.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [token-auth.handler :refer :all]
            [token-auth.helpers :refer [parse-body create-test-users]]
            [token-auth.queries.query-defs :as query]
            [cheshire.core :as ch]))

(defn setup-teardown [f]
  (try
    (query/create-registered-user-table-if-not-exists!)
    (f)
    (finally
      (query/drop-user-table!))))


(use-fixtures :each setup-teardown)

(deftest test-retrieving-all-users
  (testing "all-users route returns an array of all users"
    (create-test-users)
    (let [response (app (mock/request :get "/api/all-users"))
          body     (parse-body (:body response))]
      (is (= (:id (first body)) 1))
      (is (= (:email (first body)) "test@test.com"))
      (is (= (:username (first body)) "test.user"))
      (is (= (:id (second body)) 2))
      (is (= (:email (second body)) "test2@test.com"))
      (is (= (:username (second body)) "test.user2")))))
