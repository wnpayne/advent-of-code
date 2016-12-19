(ns advent-of-code.day2.2
  (:gen-class)
  (:require [advent-of-code.day2.1 :as day2-1]))

(def keyboard2 [[nil nil 1 nil nil]
               [nil 2 3 4 nil]
               [5 6 7 8 9]
               [nil \A \B \C nil]
               [nil nil \D nil nil]])

(defn -main [& args]
  (println (str "Keycode is " (day2-1/walk (day2-1/get-input) keyboard2))))
