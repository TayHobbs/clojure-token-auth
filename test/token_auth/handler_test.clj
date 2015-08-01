(ns token-auth.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [token-auth.handler :refer :all]
            [token-auth.helpers :refer [parse-body]]
            [token-auth.queries.query-defs :as query]))

(defn setup-teardown [f]
  (try
    (query/create-registered-user-table-if-not-exists!)
    (query/insert-test-user)
    (f)
    (finally
      (query/drop-user-table!))))

(use-fixtures :each setup-teardown)

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/example"))
          body     (parse-body (:body response))]
      (is (= (:id (first body)) 1))
      (is (= (:email (first body)) "test@test.com"))
      (is (= (:username (first body)) "test.user")))))
