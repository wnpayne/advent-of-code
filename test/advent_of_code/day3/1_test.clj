(ns advent-of-code.day3.1-test
  (:gen-class)
  (:require [clojure.test :refer :all]
            [advent-of-code.core]
            [advent-of-code.day3.1 :refer :all]))

(deftest provided-test-1
  (testing "5 10 25 is invalid."
    (is (= false
           (valid-triangle? [5 10 25])))))

(deftest working-test-1
  (testing "3 4 5"
    (is (= true
           (valid-triangle? [3 4 5])))))
