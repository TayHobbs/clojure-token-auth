(ns token-auth.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [token-auth.handler :refer :all]
            [token-auth.queries.query-defs :as query]))

(defn setup-teardown [f]
  (try
    (query/create-registered-user-table-if-not-exists!)
    (f)
    (finally
      (query/drop-user-table!))))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/example"))]
      (is (= (:status response) 200)))))
