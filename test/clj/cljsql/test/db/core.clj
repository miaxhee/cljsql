(ns cljsql.test.db.core
  (:require [cljsql.db.core :refer [*db*] :as db]
            [luminus-migrations.core :as migrations]
            [clojure.test :refer :all]
            [clojure.java.jdbc :as jdbc]
            [cljsql.config :refer [env]]
            [mount.core :as mount]))

(use-fixtures
  :once
  (fn [f]
    (mount/start
      #'cljsql.config/env
      #'cljsql.db.core/*db*)
    (migrations/migrate ["migrate"] (select-keys env [:database-url]))
    (f)))

(deftest test-messages
  (jdbc/with-db-transaction [t-conn *db*]
    (jdbc/db-set-rollback-only! t-conn)
	(let [timestamp (java.util.Date.)]
		(is (= 1 (db/save-message!
				   t-conn
				   {:name         "Google"
					:url "https://www.google.ch"
					:message  "The Google URL"
					:timestamp timestamp}
					{:connection t-conn})))
		(is (= {:name         "Google"
				:url "https://www.google.ch"
				:message  "The Google URL"
				:timestamp timestamp}
			   (-> (db/get-messages t-conn {})
			   (first)
			   (select-keys [:name :url :message :timestamp])))))))
