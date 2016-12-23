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

(defn valid-output-position? [in-hash]
  (let [pos (int (nth in-hash 5))]
    (and (>= pos 48)
         (> 56 pos))))


(defn password-from-hashes [hashlist]
  (->> hashlist
       (map (fn [x] [(nth x 5) (nth x 6)]))
       (sort)
       (map #(second %))
       (apply str)))

(defn real-decrypt-password [password]
  (->> password
       (hash-seq)
       (filter starts-with-five-zeros?)
       (filter valid-output-position?)
       (take 8)
       (password-from-hashes)))

(defn real-decrypt-password-test [password]
  (->> password
       (hash-seq)
       (filter starts-with-five-zeros?)
       (filter valid-output-position?)
       (take 8)))
