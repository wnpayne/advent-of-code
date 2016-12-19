(ns advent-of-code.day1.2
  (:gen-class)
  (:require [advent-of-code.day1.common :refer :all]))


(defn new-angle-from-step
  "Takes curpos and step and returns new angle."
  [curpos step]
  (rem (+ (first curpos) (angle-from-step (first step)))
       (* 2 Math/PI)))

(defn vec-from-angle
  "Returns cartesion coords of unit vector w/ angle."
  [angle]
  (vec (map int [(Math/cos angle) (Math/sin angle)])))

(defn scale-vec
  "Given cartension vector and scalar, return scaled vector."
  [invec scale-factor]
  (vec (map #(* scale-factor %) invec)))

(defn take-steps-in-direction
  "Takes start position, direction, and number of steps
  and returns history of stops visited." 
  [start direction numsteps]
  (let [combinefn (comp vec (partial map +))
        scalefn (partial scale-vec direction)]
    (lazy-seq (map combinefn (repeat start) (map scalefn (take numsteps (iterate inc 1)))))))

(defn combine-upto-collision
  "Concat two lists until a collision is found.
  Returns tuple of (was-collision-found? combined lists)"
  [history newelems]
  (reduce (fn [coll elem]
            (if-let [collision (some (set [elem]) (second coll))]
              (reduced [true (conj (second coll) elem)])
              [false (conj (second coll) elem)]))
          [false history]
          newelems))

(defn take-step [curpos step]
  "Takes position and step and returns new position.
  Position is [angle position-history]
  position-history is [position position position ...]"
  (let [realcurpos (second curpos)
        newangle (new-angle-from-step realcurpos step)
        newdirection (vec-from-angle newangle)
        newpoints (take-steps-in-direction (last (second realcurpos)) newdirection (second step))
        newpos (combine-upto-collision (second realcurpos) newpoints)]
    [(first newpos) [newangle (second newpos)]]))

(defn walk
  "Walk a given path until end or first collision."
  ([path] (walk path [false [(/ Math/PI 2) [[0 0]]]]))
  ([path pos]
   (if-let [step (first path)]
     (let [step-results (take-step pos step)]
       (if (first step-results)
         step-results
         (recur (rest path) step-results)))
     pos)))


(defn -main [& args]
  (let [input
        (get-input (do
                       (print "Input file? ")
                       (flush)
                       (read-line)))]
    (println (str "Santa's workshop is: "
                  (taxicab-distance (last (second (second (walk input)))))
                  " blocks away."))))
