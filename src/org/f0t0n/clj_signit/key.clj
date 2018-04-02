(ns org.f0t0n.clj-signit.key
  (:require [org.f0t0n.clj-signit.constants :as const]))


(defn generate
  [& {:keys [key-length key-chars]
      :or {key-length const/key-length key-chars const/key-chars}}]
  (let [chars-cnt (count key-chars)]
    (->> (range key-length)
         (map (fn [x](nth key-chars (.nextInt const/random chars-cnt))))
         (apply str))))
