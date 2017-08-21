(ns org.f0t0n.clj-signit.utils)


(defn char-range
  ([start end]
   (map char (range (int start) (inc (int end)))))
  ([range]
   (let [[start end] range]
     (char-range start end))))


(defn hex [s]
  (apply str (map #(format "%x" %) s)))
