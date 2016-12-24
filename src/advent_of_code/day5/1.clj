(ns advent-of-code.day5.1
  (:gen-class))

(import '[java.security MessageDigest])

(defn md5 [s]
  "Probably not the fastest, but thanks
  http://sdegutis.github.io/2016-03-07/clojure-md5-no-deps/"
  (->> (-> (MessageDigest/getInstance "md5")
           (.digest (.getBytes s "UTF-8")))
       (map #(format "%02x" %))
       (apply str)))

(defn starts-with-five-zeros? [string]
  (re-find #"^00000" string))

(defn hash-seq [input]
  (map (fn [x y]
         (md5 (str x y)))
       (repeat input)
       (iterate inc 1)))
        
(defn decrypt-password [password]
  (->> password
       (hash-seq)
       (filter starts-with-five-zeros?)
       (take 8)
       (map #(nth % 5))
       (apply str)))

;; should be 2.clj here

(defn valid-output-position?
  "hacky soln that checks if int ascii value is between \0 and \7"
  [in-hash]
  (let [pos (int (nth in-hash 5))]
    (and (>= pos 48)
         (> 56 pos))))

(defn password-reducer
  "take seq of vector pairs of [position value] and produce
  a seq of length 8 with one value per position"
  [coll elem]
  (if (= 8 (count coll))
    (reduced coll)
    (if (some #{(first elem)} (map (partial first) coll))
      coll
      (conj coll elem))))

(defn real-decrypt-password
  "decrypt password. try to figure out a way to make this parallel..."
  [password]
  (->> password
       (hash-seq)
       (filter starts-with-five-zeros?)
       (filter valid-output-position?)
       (map #(vector (nth % 5) (nth % 6)))
       (reduce password-reducer [])
       (map #(vector (- (int (first %)) 48) (second %)))
       (sort)
       (map #(second %))
       (apply str)))
