(ns gimme-any.query)

(defn- get-val-or-empty-string
  [wrapped-value]
  (nth wrapped-value 0 "")) 

(defn- query-fn 
  [prefix]
  (fn [query]
    (->> [query]
      (filter #(not (= "" %)))
	  (map #(str prefix %))
	  (get-val-or-empty-string))))
   

(def google-query
  (query-fn "https://www.google.pl/search?q="))

(def bing-query
  (query-fn "https://www.bing.com/search?q="))

(def yahoo-query
  (query-fn "https://search.yahoo.com/search;?p="))

