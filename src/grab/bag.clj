(ns grab.bag
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as jetty]))

(def my-name (atom ""))

; let's make a handler another way
(defroutes routes
  (GET "/" [] (str "<h2>Grabbe Bagge</h2>" "Last name: " @my-name))
  (POST "/hey" [name] (reset! my-name name)))

(def my-handler (handler/site routes))

(defn -main [port]
  (jetty/run-jetty #'my-handler {:port (Integer. port) :join? false}))
