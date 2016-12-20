(ns advent-of-code.day3.2
  (:gen-class)
  (:require [advent-of-code.day3.1 :refer [get-input valid-triangle?]]))


(defn transpose [m]
  "Tranpose matrix."
  (apply mapv vector m))

(defn fix-input [input]
  "Returns transposed, partitioned by 3, flattened input."
  (reduce concat
          (map (partial partition 3)
               (transpose input))))


(defn -main [& args]
  (println (str "Number of valid triangles: "
                (count (filter valid-triangle? (fix-input (get-input)))))))
