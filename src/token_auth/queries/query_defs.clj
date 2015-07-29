(ns token-auth.queries.query-defs
  (:require [environ.core :refer [env]]
            [yesql.core   :refer [defqueries]]))

(def db-connection {:connection (env :database-url)})

(defqueries "token_auth/tables/user.sql"             db-connection)
(defqueries "token_auth/queries/user/registered.sql" db-connection)
