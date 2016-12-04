(ns advent-of-code.core-test
  (:require [clojure.test :refer :all]
            [advent-of-code.core :refer :all]
            [advent-of-code.day1-1 :refer :all]))

(deftest provided-test-1
  (testing "R2,L3 should be 5"
    (is (= 5 (taxicab-distance (second (walk '((:R 2) (:L 3)))))))))

(deftest provided-test-2
  (testing "R2,R2,R2 should be 2"
    (is (= 2 (taxicab-distance (second (walk '((:R 2) (:R 2) (:R 2)))))))))

(deftest provided-test-3
  (testing "R5,L5,R5,R3 should be 12"
    (is (= 12 (taxicab-distance (second (walk '((:R 5) (:L 5) (:R 5) (:R 3)))))))))

(deftest circle-left
  (testing "4 lefts make nothing"
    (is (zero? (taxicab-distance (second (walk '((:L 1) (:L 1) (:L 1) (:L 1)))))))))

(deftest circle-left-big
  (testing "4 big lefts make nothing"
    (is (zero? (taxicab-distance (second (walk '((:L 10) (:L 10) (:L 10) (:L 10)))))))))

(deftest circle-right
  (testing "4 rights make nothing"
    (is (zero? (taxicab-distance (second (walk '((:R 1) (:R 1) (:R 1) (:R 1)))))))))

(deftest circle-right-big
  (testing "4 rights make nothing"
    (is (zero? (taxicab-distance (second (walk '((:R 10) (:R 10) (:R 10) (:R 10)))))))))

(deftest unit-circle
  (testing "unit (of 2) circle"
    (is (= 2 (taxicab-distance (second (walk '((:R 1) (:L 1)))))))
    (is (= 2 (taxicab-distance (second (walk '((:R 1) (:R 1)))))))
    (is (= 2 (taxicab-distance (second (walk '((:L 1) (:R 1)))))))
    (is (= 2 (taxicab-distance (second (walk '((:L 1) (:L 1)))))))))
