(ns grab.bag
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as jetty]))

(def my-name (atom {:name "peter"}))

(defn render-status []
  (str @my-name))

(defn update-status! [name]
  (reset! my-name (transform my-name name)))

(defn transform [my-name name]
  (assoc @my-name :name name))

(defroutes routes
  (GET "/" [] (str "<h2>Grabbe Bagge</h2>" (render-status)))
  (POST "/hey" [name] (update-status! name)))

(def my-handler (handler/site routes))

(defn -main [port]
  (jetty/run-jetty #'my-handler {:port (Integer. port) :join? false}))
