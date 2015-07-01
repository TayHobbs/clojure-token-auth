(ns token-auth.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [token-auth.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/example"))]
      (is (= (:status response) 200)))))
