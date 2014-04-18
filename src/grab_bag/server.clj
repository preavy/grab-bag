(ns grab-bag.server
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as jetty]
            [grab-bag.data :as data]))

(defn render-status []
  (str @data/choices))

(defroutes routes
  (GET "/" [] (str "<h2>Grabbe Bagge</h2>" (render-status)))
  (POST "/hey" [person choice] (swap! grab-bag.data/choices grab-bag.data/grab (keyword person) (keyword choice))))

(def my-handler (handler/site routes))

(defn -main [port]
  (jetty/run-jetty #'my-handler {:port (Integer. port) :join? false}))
