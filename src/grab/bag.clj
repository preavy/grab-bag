(ns grab.bag
  (:require [compojure.core :refer [defroutes GET POST]]
            [ring.adapter.jetty :as jetty]))

(defroutes routes
  (GET "/" [] "<h2>Grab Bag</h2>")
  (POST "/hey" [] "reply"))

(defn -main [port]
  (jetty/run-jetty #'routes {:port (Integer. port) :join? false}))
