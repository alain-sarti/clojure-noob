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