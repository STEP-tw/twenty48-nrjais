(ns twenty48.core
  (:gen-class))

(def zeroes
  (comp
   (partial apply repeat)
   reverse
   (partial list 0)
   (partial apply -)
   (juxt first (comp count second))))

(def pad-left
  (comp
   (partial apply concat)
   (juxt second zeroes)))

(def move-left
  (comp
   (partial map (partial apply +))
   (partial mapcat (partial partition-all 2))
   (partial partition-by identity)
   (partial filter (comp not zero?))))

(def move-left-with-pad
  (comp
   pad-left
   (juxt count move-left)))

(def move-right-with-pad
  (comp reverse move-left-with-pad reverse))

(def move-grid-right
  "Moves an entire grid to the right"
  (partial map move-right-with-pad))

(def move-grid-left
  "Moves an entire grid to the left"
  (partial map move-left-with-pad))

(def transpose (partial apply map list))

(def move-grid-down
  "Moves an entire grid down"
  (comp
   transpose
   move-grid-right
   transpose))

(def move-grid-up
  "Moves an entire grid up"
  (comp
   transpose
   move-grid-left
   transpose))
