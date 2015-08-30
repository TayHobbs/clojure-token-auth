(ns token-auth.user-creation-tests
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

(deftest test-creating-user-successfully
  (testing "creating a user successfully"
    (let [response (create-user {:email "x@x.com" :username "thobbs" :password "asdf"})
          body     (parse-body (:body response))]
      (is (= (:status response) 201))
      (is (= (:email body) "x@x.com"))
      (is (= (:username body) "thobbs")))))

(deftest test-creating-user-with-email-taken
  (testing "creating a user with email already taken"
    (create-test-users)
    (let [response (create-user {:email "test@test.com" :username "thobbs" :password "asdf"})
          body     (parse-body (:body response))]
      (is (= (:status response) 409))
      (is (= (:error body) "Email already exists")))))

(deftest test-creating-user-with-username-taken
  (testing "creating a user with username already taken"
    (create-test-users)
    (let [response (create-user {:email "x@x.com" :username "test.user" :password "asdf"})
          body     (parse-body (:body response))]
      (is (= (:status response) 409))
      (is (= (:error body) "Username already exists")))))

(deftest test-creating-user-with-email-and-username-taken
  (testing "creating a user with username and email already taken"
    (create-test-users)
    (let [response (create-user {:email "test@test.com" :username "test.user" :password "asdf"})
          body     (parse-body (:body response))]
      (is (= (:status response) 409))
      (is (= (:error body) "Username and Email already exists")))))
