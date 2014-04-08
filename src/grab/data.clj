(ns grab.data)

(def people '[a b c d e f g h i j k l m o n p q r s t u v w x y z])

(def choices {
              :flumps {:max 2 :chosen-by '[a]}
              :space-raiders {:max 6 :chosen-by '[a]}
              :smarties {:max 6 :chosen-by []}
              :jelly-tots {:max 6 :chosen-by []}
              :buttons {:max 6 :chosen-by []}
              :dib-dabs {:max 6 :chosen-by []}
              })

(defn already-has
  [person choice]
  (not= (.indexOf (get-in choices [choice :chosen-by]) person) -1))

(defn in-stock
  [choice]
  (let [record (choice choices)
        sold (count (:chosen-by record))
        max (:max record)]
    (< sold max)))

(defn choices-left
  [person]
  (->> choices
       vals
       (map :chosen-by)
       flatten
       count
       (- 3)))

(defn grab
  [person choice]
  (update-in choices [choice :chosen-by] (fn [x] (conj x person))))
