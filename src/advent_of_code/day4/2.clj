(ns advent-of-code.day4.2
  (:gen-class)
  (:require [clojure.string :as string]
            [clojure.pprint]
            [advent-of-code.day4.1 :as day4-1]))


(defn iterate-char [in-char inc-num]
  (let [real-inc (mod inc-num 26)
        new-char (+ (int in-char) real-inc)]
    (if (> new-char 122)
      (char (+ 96 (- new-char 122)))
      (char new-char))))

(defn decrypt-word
 ([word inc-num] (decrypt-word word inc-num ""))
 ([word inc-num output]
  (if-let [next-char (first word)]
    (recur (apply str (drop 1 word))
           inc-num
           (str output (iterate-char next-char inc-num)))
    output)))

(defn decrypt-line [line]
  (string/join " " (map #(decrypt-word % (second line)) (first line))))


(defn find-north-pole-storage [line]
  (let [decrypted-name (decrypt-line line)]
    (if-let [northfound (re-find #"north" decrypted-name)]
      (list decrypted-name (second line))
      nil)))


(defn -main [& args]
  (run! println
        (filter identity
                (map find-north-pole-storage 
                     (filter day4-1/valid-line?
                             (map day4-1/process-line (day4-1/get-input)))))))

;;(defn -main [& args]
;;  (let 
;;    [decrypted-lines (map decrypt-line
;;                          (filter day4-1/valid-line? 
;;                                  (map day4-1/process-line (day4-1/get-input))))]
;;    (do
;;        (println "Decrypted lines:")
;;        (run! println decrypted-lines))))
