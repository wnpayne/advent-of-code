(ns advent-of-code.day1.2-test
  (:require [clojure.test :refer :all]
            [advent-of-code.day1.2 :refer :all]))

(deftest positive-scale-1
  (testing "[1,1] * 3 = [3,3]"
    (is (= [3 3] (scale-vec [1 1] 3)))))

(deftest positive-scale-2
  (testing "[1,2] * 3 = [3,6]"
    (is (= [3 6] (scale-vec [1 2] 3)))))

(deftest negative-scale-1
  (testing "[1,1] * -3 = [-3,-3]"
    (is (= [-3 -3] (scale-vec [1 1] -3)))))

(deftest negative-scale-2
  (testing "[1,2] * -3 = [-3,-6]"
    (is (= [-3 -6] (scale-vec [1 2] -3)))))


(deftest vec-from-angle-right
  (testing "0 is [1 0]"
    (is (= [1 0] (vec-from-angle 0)))))

(deftest vec-from-angle-right
  (testing "pi/2 is [0 1]"
    (is (= [0 1] (vec-from-angle (/ Math/PI 2))))))

(deftest vec-from-angle-right
  (testing "pi is [-1 0]"
    (is (= [-1 0] (vec-from-angle Math/PI)))))

(deftest vec-from-angle-right
  (testing "3pi/2 is [0 -1]"
    (is (= [0 -1] (vec-from-angle (* Math/PI 3/2))))))


(deftest step-right
  (testing "from origin 3 steps diagonal should be ([1 1] [2 2] [3 3])"
    (is (= '([1 1] [2 2] [3 3]) (take-steps-in-direction [0 0] [1 1] 3)))))

(deftest step-down
  (testing "from [4 0] take 4 steps in [0 -1] => [4 -4]"
    (is (= '([4 -1] [4 -2] [4 -3] [4 -4])
           (take-steps-in-direction [4 0] [0 -1] 4)))))

(deftest combine1
  (testing "[1 2 3] + [1 2] is [1 2 3 1]"
    (is (= [true [1 2 3 1]]
           (combine-upto-collision [1 2 3] [1 2])))))

(deftest combine2
  (testing "[1 2 3] + [4 5 6] is [1 2 3 4 5 6]"
    (is (= [false [1 2 3 4 5 6]]
           (combine-upto-collision [1 2 3] [4 5 6])))))

(deftest combine3
  (testing "[[1 1] [2 2]] + [[1 1] [3 3]]"
    (is (= [true [[1 1] [2 2] [1 1]]]
           (combine-upto-collision [[1 1] [2 2]] [[1 1] [3 3]])))))

(deftest combine4
  (testing "[[1 1] [2 2]] + [[1 1] [3 3]]"
    (is (= [false [[1 1] [2 2] [3 3] [4 4]]]
           (combine-upto-collision [[1 1] [2 2]] [[3 3] [4 4]])))))

(deftest take-step-1
  (testing "R5 should be [false [0 [[0 0] [1 0] [2 0]..."
    (is (= [false [0.0 [[0 0] [1 0] [2 0] [3 0] [4 0] [5 0]]]]
           (take-step [false [(/ Math/PI 2) [[0 0]]]] [:R 5])))))


(deftest provided-walk-test-1
  (testing "R8, R4, R4, R8 collides at [4 0]"
    (is (= [4 0]
           (last (second (second (walk [[:R 8] [:R 4] [:R 4] [:R 8]]))))))))
