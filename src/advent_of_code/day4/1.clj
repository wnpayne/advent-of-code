(ns advent-of-code.day4.1
  (:gen-class)
  (:require [clojure.string :as string]))


(defn get-input []
  "prompt for input and return vector of lines."
  (let [inputfile (do (print "Input file?")
                      (flush)
                      (read-line))]
        (string/split (slurp inputfile) #"\n")))

(defn process-line [line]
  "process line into vec of enc-name sec-id chksum"
  (let [splitline (string/split line #"-")
        enc-name  (apply str (butlast splitline))
        tail      (last splitline)
        sec-check (string/split tail #"\[|\]")]
    (list enc-name (Integer/parseInt (first sec-check)) (second sec-check))))


(defn char-freq [enc-string]
  "return sorted character frequency map"
  (sort (fn [x y] (> (second x) (second y)))
        (frequencies enc-string)))

(defn buckets-from-char-freq [cf]
  "return list of buckets for chars"
  (vals (frequencies (vals cf))))

(defn char-list-from-char-freq [cf]
  "sorted chars from char-list"
  (keys cf))

(defn process-bucket [bucket char-list]
  "update output by putting part of char-list in bucket"
  (apply str (sort (take bucket char-list))))

(defn chksm-from-buckets
  "from buckets and char-list return checksum"
  ([buckets char-list] (chksm-from-buckets buckets char-list ""))
  ([buckets char-list output]
   (if-let [next-bucket (first buckets)]
     (recur (rest buckets)
            (drop next-bucket char-list)
            (str output (process-bucket next-bucket char-list)))
     (apply str (take 5 output)))))

(defn checksum-from-string [enc-string]
  "calculate checksum from enc-string"
  (let [cf (char-freq enc-string)
        buckets (buckets-from-char-freq cf)
        char-list (char-list-from-char-freq cf)]
      (chksm-from-buckets buckets char-list)))


(defn valid-checksum? [enc-string chksum]
  "is checksum valid for enc-string?"
  (= chksum
     (checksum-from-string enc-string)))

(defn sum-valid-sectors [lines]
  "from list of lines sum sector ids of lines with valid checksums"
  (reduce +
    (map #(second %)
         (filter (fn [line]
                   (valid-checksum? (first line) (last line)))
                 lines))))


(defn -main [& args]
  (sum-valid-sectors (map process-line (get-input))))
