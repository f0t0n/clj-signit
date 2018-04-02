(ns org.f0t0n.clj-signit.test-signature
  (:require [clojure.test :refer :all]
            [org.f0t0n.clj-signit.signature :refer :all]
            [org.f0t0n.clj-signit.constants :as const]))


(deftest test-create
  (is
    (=
     (create "test-user-id" "test-secret-key" "Hello World!")
     (str "HMAC-SHA256 test-user-id:"
          "1be71d1df051c182b366eabfac172e18eddc5696073f39c796bd88e80239d"))))


(deftest test-parse
  (is (= (parse "     abc    def  :  ghi    ") '("abc" "def" "ghi"))))


(deftest test-verify
  (doseq
    [[digest predicate]
     {"1be71d1df051c182b366eabfac172e18eddc5696073f39c796bd88e80239d" true?
      "2be71d1df051c182b366eabfac172e18eddc5696073f39c796bd88e80239d" false?}]
    (is (predicate (verify digest "test-secret-key" "Hello World!")))))
