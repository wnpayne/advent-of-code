(ns advent-of-code.core
  (:gen-class)
  (:require [advent-of-code.day1.1 :as day1-1]
            [advent-of-code.day1.2 :as day1-2]
            [clojure.pprint :refer [pprint]]
            [clojure.string :as string]))


(def days
  {:1-1 ["Follow the path to Santa's Workshop!" day1-1/-main]
   :1-2 ["Find Santa's Workshop at the first twice crossed location." day1-2/-main]})

(defn -main
  "Prompt for input and run problem."
  [& args]
  (let [days-prompt (into {} (map #(vector (first %) (first (second %))) days))
        chosen-number (do (pprint days-prompt)
                          (print "\nInput problem number: ")
                          (flush)
                          (keyword (string/replace (read-line) #":" "")))
        problem-main (second (chosen-number days))]
    (problem-main)))
