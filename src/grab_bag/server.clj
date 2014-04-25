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

(defn render-page [[choices error-message]]
  (p/html5
   [:head]
   [:body
    [:h2 "Grabbe Bagge"]
    [:form {:action "choose" :method "POST"}
     "Person" (person-box)
     "Choice" (choices-box)
     [:input {:type "Submit"}]]
    [:div (str choices)]
    [:div (str error-message)]]))

(defroutes routes
  (GET "/" [] (render-page [@data/choices nil]))
  (POST "/choose" [person choice] (render-page (data/choose! (keyword person) (keyword choice)))))

(def route-handler (handler/site routes))

(defn -main [port]
  (jetty/run-jetty #'route-handler {:port (Integer. port) :join? false}))
