(ns advent-of-code.day5.1-test
  (:gen-class)
  (:require [clojure.test :refer :all]
            [advent-of-code.day5.1 :refer :all]))

(deftest starts-with-five-zeros
  (testing "000001 does start with five zeroes."
    (is (starts-with-five-zeros? "000001"))))

(deftest starts-with-five-zeros-2
  (testing "0001 does not start with five zeroes."
    (is (not (starts-with-five-zeros? "0001")))))

(deftest valid-output-position-1
  (testing "5081f07959a8a9f4ae71d3b870ed3f05 is valid"
    (is (valid-output-position? "5081f07959a8a9f4ae71d3b870ed3f05"))))

(deftest valid-output-position-2
  (testing "5081fe7959a8a9f4ae71d3b870ed3f05 is not valid"
    (is (not (valid-output-position? "5081fe7959a8a9f4ae71d3b870ed3f05")))))

(deftest decrypt-1
  (testing "decryption of abc is 18f47a30"
    (is (= "18f47a30"
           (decrypt-password "abc")))))

(deftest decrypt-2
  (testing "decryption of abc is 05ace8e3"
    (is (= "05ace8e3"
           (real-decrypt-password "abc")))))
