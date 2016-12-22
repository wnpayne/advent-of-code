(ns advent-of-code.day4.2-test
  (:gen-class)
  (:require [clojure.test :refer :all]
            [advent-of-code.day4.1 :refer [process-line]]
            [advent-of-code.day4.2 :refer :all]))

(deftest provided-test-1
  (testing "qzmt-zixmtkozy-ivhz-343[zimth] is very encrypted name"
    (is (= "very encrypted name"
           (decrypt-line (process-line "qzmt-zixmtkozy-ivhz-343[zimth]"))))))

(deftest iterate-char-1
  (testing "b from a"
    (is (= \b
           (iterate-char \a 1)))))

(deftest iterate-char-2
  (testing "a from z"
    (is (= \a
           (iterate-char \z 1)))))

(deftest iterate-char-2
  (testing "a from z"
    (is (= \a
           (iterate-char \z 27)))))

(deftest iterate-char-3
  (testing "a to a"
    (is (= \a
           (iterate-char \a 52)))))


(deftest decrypt-word-1
  (testing "zabc + 27"
    (is (= "abcd"
           (decrypt-word "zabc" 27)))))

(deftest decrypt-word-2
  (testing "zabc + 26"
    (is (= "abcd"
           (decrypt-word "abcd" 26)))))

(deftest decrypt-word-3
  (testing "qzmt + 343 is very"
    (is (= "very"
           (decrypt-word "qzmt" 343)))))
