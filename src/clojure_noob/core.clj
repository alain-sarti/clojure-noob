(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot!"))

(defn -testing1
  [& args]
  (if true
    (do (println "Success!")
        "By Zeus's hammer!")
    (do (println "Failure!")
        "By Aquaman's trident!"))
  )

(defn -testing2
  [& args]
  (when true
    (println "Success!")
    "abra cadabra"))

(defn -equality
  [& args]
  (= 1 1))

(defn -boolean
  [& args]
  (or false nil :large_I_mean_venti :why_cant_I_just_say_large))

(defn -numbers
  []
  (println 1/5))

(defn -goaway
  [name]
  (println (str "Go away, " name)))

(defn -maps
  []
  (println (get {:first "Alain" :last "Sarti"} :first)))

(defn -nestedMaps
  []
  (println (get-in {:name {:first "Alain" :last "Sarti"}} [:name :last])))

(defn -lists
  []
  (println '(1 2 3 4)))

(defn -hashsets
  []
  (println (contains? #{3 4 5} 5)))

(defn x-chop
  "Describe the kind of chop and the victim"
  ([name chop-type]
    (println (str "I " chop-type " chop " name)))
  ([name]
    (println (str "I karate chop " name)))
  )

(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " unimportant-choices))))

(defn announce-treasure-location
     [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(defn anonymousfn
  []
  (map #(str "Hi, " %1 "!") ["Darth Vader" "Mr. Magoo"])
  )

(defn inc-maker
  [inc-by]
  #(+ %1 inc-by)
  )

(def inc3 (inc-maker 3))

(defn looping
  []
  (loop [iteration 0]
    (println (str "Iteration " iteration))
    (if (> iteration 3)
      (println "Goodbye!")
      (recur (inc iteration)))))

(defn rexpression
  []
  (println (re-find #"^left-" "left-eye"))
  (println (re-find #"^left-" "cleft-eye")))

(defn reducing
  []
  (println (reduce + 15 [1 2 3 4])))

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(defn show-first-three
  []
  (println (take-while #(< (:month %) 3) food-journal)))

(defn show-critter-greater-three
  []
  (println (filter #(> (:critter %) 3) food-journal)))

(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))

(defn show-vampire
  []
  (time (identify-vampire (range 0 1000000)))
  )

; ========= exercises ================

(defn one
  []
  (println (str "one and " "two"))
  (println (vector "a" "b"))
  (println (list 1 2 3 4))
  (println (hash-map :key1 4 :key2 5))
  (println (hash-set 1 1 3 4 5 6 6))
  )

(defn two
  [number]
  (println (+ number 100))
  )

(defn dec-maker
  [dec-by]
  #(- %1 dec-by)
  )

(defn three
  []
  (def dec9 (dec-maker 9))
  (println (dec9 10))
  )

(defn mapset
  [func coll]
  (loop [result-list #{}
         remaining (set coll)
         ]
    (if (empty? remaining)
      result-list
      (recur (conj result-list (func (first remaining))) (rest remaining))
      )
    )
  )

(defn four
  []
  (println (mapset inc [1 1 2 2]))
  )

(def asym-alien-body-parts [{:name "head" :size 4}
                            {:name "1st-arm" :size 5}
                            {:name "1st-leg" :size 6}
                            {:name "1st-tail" :size 3}
                            {:name "1st-eye" :size 1}
                            ])

(defn matching-parts
  [part]
  (if (re-find #"^1st-" (:name part))
    (loop [new-parts []
           remaining '("2nd" "3rd" "4th" "5th")]
      (if (empty? remaining)
        new-parts
        (recur (conj new-parts {:name (clojure.string/replace (:name part) #"^1st-" (str (first remaining) "-"))
                                :size (:size part)
                                })
               (rest remaining))))
    part
    ))

(defn symmetrize-body-parts
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-parts part)])))
          []
          asym-body-parts))

(defn five
  []
  (println (symmetrize-body-parts asym-alien-body-parts)))