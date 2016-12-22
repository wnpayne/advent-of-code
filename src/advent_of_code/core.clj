(ns advent-of-code.core
  (:gen-class)
  (:require [advent-of-code.day1.1 :as day1-1]
            [advent-of-code.day1.2 :as day1-2]
            [advent-of-code.day2.1 :as day2-1]
            [advent-of-code.day2.2 :as day2-2]
            [advent-of-code.day3.1 :as day3-1]
            [advent-of-code.day3.2 :as day3-2]
            [advent-of-code.day4.1 :as day4-1]
            [advent-of-code.day4.2 :as day4-2]
            [clojure.pprint :refer [pprint]]
            [clojure.string :as string]))


(def days
  {:1-1 ["Follow the path to Santa's Workshop!" day1-1/-main]
   :1-2 ["Find Santa's Workshop at the first twice crossed location." day1-2/-main]
   :2-1 ["Find the bathroom keycode before you lose control!" day2-1/-main]
   :2-2 ["Find the bathroom keycode with a more complex keypad!" day2-2/-main]
   :3-1 ["Count all valid triangles." day3-1/-main]
   :3-2 ["Count all valid triangles when reading correctly." day3-2/-main]
   :4-1 ["Sum the valid sector IDs." day4-1/-main]
   :4-2 ["Decrypt the sector names." day4-2/-main]})

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
