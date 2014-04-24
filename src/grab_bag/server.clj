(ns grab-bag.server
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as jetty]
            [grab-bag.data :as data]
            [hiccup.page :as p]))

(defn person-box []
  (p/html5 [:select])
)

(defn choices-box []
  (p/html5 [:select])
)

(defn render-status [[choices error-message]]
  (p/html5 [:head] [:body (person-box) (choices-box) [:div (str choices)] [:div (str error-message)]]))

(defroutes routes
  (GET "/" [] (str "<h2>Grabbe Bagge</h2>" (render-status [@data/choices nil])))
  (POST "/choose" [person choice] (render-status (data/choose! (keyword person) (keyword choice)))))

(def my-handler (handler/site routes))

(defn -main [port]
  (jetty/run-jetty #'my-handler {:port (Integer. port) :join? false}))
