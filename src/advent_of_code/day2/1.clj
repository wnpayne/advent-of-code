(ns advent-of-code.day2.1
  (:gen-class)
  (:require [clojure.string :as string]))


(def keyboard1 [[1 2 3]
                [4 5 6]
                [7 8 9]])

(def step-to-direction {:U [0 -1]
                        :D [0 1]
                        :R [1 0]
                        :L [-1 0]})

(defn keyword-from-char-list [charlist]
  (map (comp keyword str) charlist))

(defn get-input []
  (let [input (do
                (print "Input file? ")
                (flush)
                (read-line))]
    (map keyword-from-char-list 
         (string/split (slurp input) #"\n"))))

(defn get-key [pos keyboard]
  (try
       (nth (nth keyboard (second pos)) (first pos))
       (catch Exception e nil)))

(defn take-step [pos step keyboard]
  (let [newpos (map + pos (step-to-direction step))]
    (if-let [newkey (get-key newpos keyboard)]
        newpos
        pos)))

(defn walk-row [pos row keyboard]
  (if-let [step (first row)]
    (recur (take-step pos step keyboard) (rest row) keyboard)
    [(get-key pos keyboard) pos]))

(defn walk
  ([path keyboard] (walk [1 1] path keyboard))
  ([pos path keyboard]
   (first (reduce (fn [coll elem]
                    (let [newpos (walk-row (second coll) elem keyboard)]
                      [(str (first coll) (first newpos)) (second newpos)]))
                  ["" pos]
                  path))))


(defn -main [& args]
    (println (str "Keycode is: " (walk (get-input) keyboard1))))
