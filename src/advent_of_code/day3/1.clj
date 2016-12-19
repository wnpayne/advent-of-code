(ns advent-of-code.day3.1
  (:gen-class)
  (:require [clojure.math.combinatorics :as combo]))


(defn make-proper-row [row]
  (map #(Integer/parseInt %) (rest (clojure.string/split row #" +"))))

(defn get-input []
  (let [inputfile   (do (print "Input file? ")
                    (flush)
                    (read-line))
        input       (slurp inputfile)
        split-input (clojure.string/split input #"\n")]
    (map make-proper-row split-input)))

(defn valid-triangle? [tryangle]
  (every? identity
    (map (fn [elem]
           (> (reduce + elem)
              (first (remove (set elem) tryangle))))
         (combo/combinations tryangle 2))))

(defn -main [& args]
  (count (filter valid-triangle? (get-input))))
