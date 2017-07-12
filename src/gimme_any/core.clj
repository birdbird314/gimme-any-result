(ns gimme-any.core
  (:require [gimme-any.parse :refer :all]
            [gimme-any.query :refer :all]))

(def result-retrievers-functions
  [[(comp parse-google  slurp google-query) "google"]
   [(comp parse-bing    slurp bing-query) "bing"]
   [(comp parse-yahoo   slurp yahoo-query) "yahoo"]])

(defn gimme-any
  [query]
  (let [result (promise)]
    (doall 
	  (map 
	    (fn [[retrieve source]]
		  (future (deliver result {:source source :results (retrieve query)}))) 
	    result-retrievers-functions))
	@result))
	
