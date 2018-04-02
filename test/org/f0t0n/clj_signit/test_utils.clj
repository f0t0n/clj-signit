(ns org.f0t0n.clj-signit.test-utils
  (:require [clojure.test :refer :all]
            [org.f0t0n.clj-signit.utils :refer :all]))


(deftest test-char-range
    (is (= (char-range 65 67) '(\A \B \C)))
    (is (= (char-range '(65 67)) '(\A \B \C))))


(deftest test-char-range-negative-throws
    (is (thrown? IllegalArgumentException (doall  (char-range -1 0)))))


(deftest test-hex
    (is (= (hex (.getBytes "B")) "42")))
