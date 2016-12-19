(ns advent-of-code.day1.1
  (:gen-class)
  (:require [advent-of-code.day1.common :refer :all]))


(defn take-step [curpos step]
  "Takes current position and step and returns new position
   position is vector [angle position], e.g. [0 [1 1]]"
  (let [newangle (rem (+ (first curpos) (angle-from-step (first step))) (* 2 Math/PI))
        newdirection [(Math/cos newangle) (Math/sin newangle)]
        changevector (map int (map (fn [x] (* (second step) x)) newdirection))
        newpos (map + (second curpos) changevector)]
    [newangle newpos]))

(defn walk
  "Walk a given path and return final position."
  ([path] (walk path [(/ Math/PI 2) [0 0]]))
  ([path pos]
   (if (empty? path)
     pos
     (recur (rest path) (take-step pos (first path))))))

(defn verbose-walk [input]
  "Verbosely walk each subpath of a path."
  (map (fn [x]
    (let [walkval (walk (take x input)) distance (taxicab-distance (second walkval))] {x [walkval distance]}))
    (take (count input) (iterate inc 1))))


(defn -main [& args]
  (let [input (get-input
        (do
          (print "Input file? ")
          (flush)
          (read-line)))]
    (println (str "Santa's workshop is: " (taxicab-distance (second (walk input))) " blocks away."))))
