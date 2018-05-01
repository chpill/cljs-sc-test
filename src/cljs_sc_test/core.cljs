(ns cljs-sc-test.core
  (:require [sc.api]))



(defn f [x]
  (let [a (* 2 x)
        b (* 3 x)
        c (* x x)]
    (sc.api/spy (+ a b c))))


(def plop (f 4))


#_(sc.api/letsc 1
              (js/console.log a))

