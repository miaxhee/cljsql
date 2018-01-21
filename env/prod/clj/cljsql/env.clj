(ns cljsql.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[cljsql started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[cljsql has shut down successfully]=-"))
   :middleware identity})
