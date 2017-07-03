(ns lang.core
  (:gen-class)
  (:require [clojure.data.csv :as csv]
         [clojure.java.io :as io]))



(def words {:nouns ["cup" "man" ]
            :verbs ["likes"]
            } )


(def words [{:lt "medis" :en "tree" :ru "derevo" } {:lt "puodelis" :en "cup"} ])


(slurp "german1.csv" )


(defn csv-data->maps [csv-data]
  (map zipmap
       (->> (first csv-data) ;; First row is the header
            (map keyword) ;; Drop if you want string keys instead
            repeat)
       (rest csv-data)))

(defn read-dict [file] (with-open [reader (io/reader file)]
  (doall
   (csv/read-csv reader))))


(def german-words (doall (map #(let [[ger & en] % ] {:ger ger :en (first en)})   (read-dict "german1.csv"))))


(def spanish-words (doall (map #(let [[ger & en] % ] {:es ger :en (first en)})   (read-dict "spanish.csv"))))




(def sentences ["Augo girioje medis" "mano puodelis didesnis" "I like tree" "I have a cup" ])

(defn ask [words lang1 lang2 sentences] 
  (let [w        (rand-nth words)
        word     (w lang1)
        expected (w lang2)
        se       (first (filter #(.contains % word) sentences ))
        ]
(do    (println (str (name lang1) ": " (w lang1) ))
    (println (str (name lang2) ": "(w lang2) ))
  (println (str "type " (name lang2) ":")))
;  (println (str "sample: " se))        
(let [resp (str (read-line))]
        (println (if (= expected resp)
           "Good" (str "expected :" expected " but was " resp )  )))))


(defn find-word [word dict]
  (first (filter #(= word (% :en)) dict)))

(def dict (map #(merge (find-word (% :en) german-words) (or % {})) spanish-words))

(count (filter #(contains? % :es) dict))
(count dict)




;(count german-words)
;(first german-words)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  
  (println "Hello, lets learn german!")
  (println "Disctionary has " (count german-words) " words" )
  (println "Words will be picked at random." )
  (println "You must type them in to move on.")
  (loop []    
    (ask (take 1000 spanish-words) :en :es [])
    (recur)
    )

  )
