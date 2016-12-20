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

(defn valid-unique-triangle? [tryangle]
  (every? identity
    (map (fn [elem]
           (> (reduce + elem)
              (first (remove (set elem) tryangle))))
         (combo/combinations tryangle 2))))

(defn valid-one-repeat-triangle?
  "Triangle is valid if repeated side length is larger than absolute difference
  between repeated and singular side."
  [tryangle tryset]
  (let [repeated-val (first (first (filter #(> (val %) 1) (frequencies tryangle))))
        other (first (remove #{repeated-val} tryset))]
    (> repeated-val (Math/abs (- repeated-val other)))))

(defn valid-triangle? [tryangle]
  (let [tryset (set tryangle)
        setlen (count tryset)]
    (condp = setlen
      1 true ;equilateral triangles always valid
      2 (valid-one-repeat-triangle? tryangle tryset)
      3 (valid-unique-triangle? tryangle))))

(defn -main [& args]
  (println (str "Number of valid triangles: "
                (count (filter valid-triangle? (get-input))))))
