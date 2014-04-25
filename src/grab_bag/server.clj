(ns grab-bag.server
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as jetty]
            [grab-bag.data :as data]
            [hiccup.page :as p]))

(defn person-box []
  [:select {:name "person"} (map #(vector :option %) data/people)])

(defn choices-box []
  [:select {:name "choice"} (map #(vector :option %) (keys @data/choices))]
)

(defn render-status [[choices error-message]]
  (p/html5 [:head] [:body [:form {:action "choose" :method "POST"} "Person" (person-box) "Choice" (choices-box) [:input {:type "Submit"}]] [:div (str choices)] [:div (str error-message)]]))

(defroutes routes
  (GET "/" [] (str "<h2>Grabbe Bagge</h2>" (render-status [@data/choices nil])))
  (POST "/choose" [person choice] (render-status (data/choose! (keyword person) (keyword choice)))))

(def my-handler (handler/site routes))

(defn -main [port]
  (jetty/run-jetty #'my-handler {:port (Integer. port) :join? false}))
