(ns advent-of-code.day1.common
  (:gen-class)
  (:require [clojure.string :as string]
            [clojure.math.numeric-tower :as numeric-tower]))

;; convert step instruction to rotation delta
(def angle-from-step {:R (- (/ Math/PI 2)) :L (/ Math/PI 2)})

(defn get-input [input-path]
  "Parse provided problem input and return a list of directions.
   Each direction is of the form (<keyword> <int>) with keyword = :R or :L"
  (let [input (string/split (slurp input-path) #", |\n")]
    (map (fn [elem]
           (list (keyword (str (first elem))) (Integer/parseInt (apply str (rest elem)))))
         input)))

(defn taxicab-distance
  "Taxicab distance between v1 and v2
   e.g. |v1_x - v2_x| + |v1_y - v2_y|"
  ([v1] (taxicab-distance v1 [0 0]))
  ([v1 v2] (reduce + (map numeric-tower/abs (map - v1 v2)))))
