(ns token-auth.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [token-auth.handler :refer :all]
            [token-auth.helpers :refer [parse-body create-test-users]]
            [token-auth.queries.query-defs :as query]
            [cheshire.core :as ch]))

(defn create-user [user-map]
  (app (-> (mock/request :post "/api/user" (ch/generate-string user-map))
           (mock/content-type "application/json"))))

(defn setup-teardown [f]
  (try
    (query/create-registered-user-table-if-not-exists!)
    (f)
    (finally
      (query/drop-user-table!))))


(use-fixtures :each setup-teardown)

(deftest test-app
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

(deftest test-app
  (testing "creating a user successfully"
    (let [response (create-user {:email "x@x.com" :username "thobbs" :password "asdf"})
          body     (parse-body (:body response))]
      (is (= (:status response) 201))
      (is (= (:email body) "x@x.com"))
      (is (= (:username body) "thobbs")))))

(deftest test-app
  (testing "creating a user with email already taken"
    (create-test-users)
    (let [response (create-user {:email "test@test.com" :username "thobbs" :password "asdf"})
          body     (parse-body (:body response))]
      (is (= (:status response) 409))
      (is (= (:error body) "Email already exists")))))

(deftest test-app
  (testing "creating a user with username already taken"
    (create-test-users)
    (let [response (create-user {:email "x@x.com" :username "test.user" :password "asdf"})
          body     (parse-body (:body response))]
      (is (= (:status response) 409))
      (is (= (:error body) "Username already exists")))))

(deftest test-app
  (testing "creating a user with username and email already taken"
    (create-test-users)
    (let [response (create-user {:email "test@test.com" :username "test.user" :password "asdf"})
          body     (parse-body (:body response))]
      (is (= (:status response) 409))
      (is (= (:error body) "Username and Email already exists")))))
