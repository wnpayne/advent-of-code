(ns advent-of-code.day1-1 (:gen-class))
(require '[clojure.string :as string])
(require '[clojure.math.numeric-tower :as numeric-tower])

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
