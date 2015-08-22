(defproject token-auth "0.1.0-SNAPSHOT"
  :description "Token Authentication"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"

  :dependencies [[org.clojure/clojure        "1.6.0"]
                 [compojure                  "1.3.1"]
                 [metosin/ring-swagger-ui    "2.1.1-M2"]
                 [metosin/ring-http-response "0.6.2"]
                 [metosin/compojure-api      "0.21.0"]
                 [postgresql/postgresql      "9.3-1102.jdbc41"]
                 [buddy                      "0.6.1"]
                 [yesql                      "0.5.0-rc1"]
                 [ring/ring-defaults         "0.1.2"]
                 [cheshire                   "5.5.0"]
                 [environ                    "1.0.0"]]

  :plugins [[lein-environ "1.0.0"]
            [lein-ring    "0.8.13"]]

  :ring {:handler token-auth.handler/app}

  :profiles {:test-local   {:dependencies [[javax.servlet/servlet-api "2.5"]
                                           [ring-mock                 "0.1.5"]]}

             ;; Set these in ./profiles.clj
             :test-env-vars {}
             :dev-env-vars  {}

             :test       [:test-local :test-env-vars]
             :dev        [:dev-env-vars :test-local]})
