(ns grab.bag
  (:require [compojure.core :refer [defroutes GET]]
            [ring.adapter.jetty :as jetty]))

(defroutes routes
  (GET "/" [] "<h2>Grab Bag</h2>"))

(defn -main []
  (jetty/run-jetty #'routes {:port 8080 :join? false}))
