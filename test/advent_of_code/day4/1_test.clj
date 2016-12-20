(ns advent-of-code.day4.1-test
  (:gen-class)
  (:require [clojure.test :refer :all]
            [advent-of-code.day4.1 :refer :all]))

(deftest test-process-line-1
  (testing "aaaaa-bbb-z-y-x-123[abxyz]"
    (is (= '("aaaaabbbzyx" 123 "abxyz")
           (process-line "aaaaa-bbb-z-y-x-123[abxyz]")))))

(deftest checksum-from-string-1
  (testing "Checksum of zggtttsol is tglosz"
    (is (= "tglos"
           (checksum-from-string "zggtttsol")))))

(deftest checksum-from-string-2
  (testing "checksum of aaaaabbbzyx is abxyz."
    (is (= "abxyz"
            (checksum-from-string "aaaaabbbzyx")))))

(deftest checksum-from-string-3
  (testing "checksum of abcdefgh is abcdefgh."
    (is (= "abcde"
            (checksum-from-string "abcdefgh")))))

(deftest checksum-from-string-4
  (testing "checksum of notarealroom is oarel"
    (is (= "oarel"
           (checksum-from-string "notarealroom")))))
