(ns cljsql.routes.home
  (:require [cljsql.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]
			[cljsql.db.core :as db]
			[bouncer.core :as b]
			[bouncer.validators :as v]))

(defn validate-message [params]
	(first
		(b/validate
			params
			:name v/required
		)
	)
)
			
(defn home-page [{:keys [flash]}]
  (layout/render
    "home.html" 
	(merge {:messages (db/get-messages)}
	(select-keys flash [:name :url :message :errors]))))

(defn about-page []
  (layout/render "about.html"))

(defn save-message! [{:keys [params]}]
	(if-let [errors (validate-message params)]
		(-> (response/found "/")
		(assoc :flash (assoc params :errors errors)))
	(do
		(db/save-message!
		(assoc params :timestamp (java.util.Date.))
		)
		(response/found "/")
	)
	)
)
 
  
(defroutes home-routes
  (GET "/" request (home-page request))
  (GET "/about" [] (about-page))
  (POST "/message" request (save-message! request)))

