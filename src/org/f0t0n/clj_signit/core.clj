(ns org.f0t0n.clj-signit.core
  (:require [org.f0t0n.clj-signit.constants :as const]))

(defn greet
  "I don't do a whole lot."
  [name]
  (println (str "Hello " name "! " const/utf-8 const/key-length)))

(greet "Bobby")
