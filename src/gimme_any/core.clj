(ns gimme-any.core
  (:require [gimme-any.parse :refer :all]
            [gimme-any.query :refer :all]))

(def result-retrievers
  [(comp parse-google  slurp google-query)
   (comp parse-bing    slurp bing-query)])

(defn gimme-any
  [query]
  (let [result (promise)]
    (doall 
	  (map 
	    (fn [retrieve]
		  (future (deliver result (retrieve query)))) 
	    result-retrievers))
	@result))
	
