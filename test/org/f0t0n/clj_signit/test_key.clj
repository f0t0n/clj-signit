(ns org.f0t0n.clj-signit.test-key
  (:require [clojure.test :refer :all]
            [org.f0t0n.clj-signit.key :refer :all]))


(deftest test-generate
    (is (= 32 (count (generate)))))
